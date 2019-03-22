package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.CompositionFeedbackService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

public class CompositionFeedbackServiceImpl implements CompositionFeedbackService {
    @Override
    public List<CompositionFeedbackDto> getFeedbackDtoByCompositionId(HttpServletRequest request) throws ServiceException {
        List<CompositionFeedbackDto> compositionFeedbackDtoList = new ArrayList<>();
        String compositionName = request.getParameter("compositionName");
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);
            GenericDao<CompositionFeedback, Integer> compositionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(CompositionFeedback.class);

            transactionManager.begin(compositionDao, userCompositionDao, compositionFeedbackDao, userDao);

            Optional<Composition> compositionOptional = ((CompositionDaoExtended) compositionDao).getByName(compositionName);
            if (compositionOptional.isPresent()) {
                List<Integer> compositionCortageIdList = ((UserCompositionDaoExtended)userCompositionDao).getCortageIdByCompositionId(compositionOptional.get().getId());

                for (Integer i : compositionCortageIdList) {
                    Optional<CompositionFeedback> compositionFeedbackOptional = compositionFeedbackDao.getByPK(i);
                    Optional<UserComposition> userCompositionOptional = ((UserCompositionDaoExtended)userCompositionDao).getByCortagePK(i);

                    if (userCompositionOptional.isPresent()) {
                        Integer userId = userCompositionOptional.get().getId();
                        Integer compositionId = userCompositionOptional.get().getCompositionId(0);

                        if (compositionFeedbackOptional.isPresent()) {
                            CompositionFeedbackDto compositionFeedbackDto = new CompositionFeedbackDto();
                            compositionFeedbackDto.setDescription(compositionFeedbackOptional.get().getFeedback());
                            compositionFeedbackDto.setTimestamp(compositionFeedbackOptional.get().getTimestamp());

                            Optional<User> userOptional = userDao.getByPK(userId);
                            userOptional.ifPresent(user -> compositionFeedbackDto.setUserName(user.getLogin()));
                            compositionFeedbackDtoList.add(compositionFeedbackDto);
                        }
                    }
                }
            }
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                throw new ServiceException(e1.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return compositionFeedbackDtoList;
    }

    @Override
    public boolean addCompositionFeedback(HttpServletRequest request) throws ServiceException {
        if (request == null) {
            return false;
        }

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<CompositionFeedback, Integer> compositionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(CompositionFeedback.class);

            transactionManager.begin(userCompositionDao, compositionDao, compositionFeedbackDao);

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userCompositionOptional.isPresent()) {
                String musicSelectionInfoName = request.getParameter("compositionName");
                Optional<Composition> compositionOptional = ((CompositionDaoExtended)compositionDao).getByName(musicSelectionInfoName);

                if(compositionOptional.isPresent()) {
                    if (userCompositionOptional.get().getCompositionIdList().contains(compositionOptional.get().getId())) {
                        CompositionFeedback compositionFeedback = new CompositionFeedback();
                        int compositionId = userCompositionOptional.get().getCompositionIdList().indexOf(compositionOptional.get().getId());
                        int cortageId = userCompositionOptional.get().getCortageId(compositionId);
                        compositionFeedback.setId(cortageId);
                        compositionFeedback.setFeedback(request.getParameter("compositionFeedback"));
                        compositionFeedback.setTimestamp(new Timestamp(new Date().getTime()));

                        compositionFeedbackDao.insert(compositionFeedback);

                        transactionManager.commit();
                        return true;
                    } else {
                        transactionManager.rollBack();
                        return false;
                    }
                } else {
                    transactionManager.rollBack();
                    return false;
                }
            } else {
                transactionManager.rollBack();
                return false;
            }

        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public List<CompositionFeedbackDto> getUserCompositionFeedbackList(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        List<CompositionFeedbackDto> compositionFeedbackDtoList = new ArrayList<>();


        TransactionManager transactionManager = new TransactionManagerImpl();
        try {

            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);
            GenericDao<CompositionFeedback, Integer> compositionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(CompositionFeedback.class);
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);

            transactionManager.begin(userCompositionDao, compositionFeedbackDao, compositionDao);

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userCompositionOptional.isPresent()) {
                List<Integer> cortageIdList = userCompositionOptional.get().getCortageIdList();
                List<Integer> compositionIdList = userCompositionOptional.get().getCompositionIdList();

                for (int each = 0; each < cortageIdList.size(); each++) {

                    Optional<CompositionFeedback> compositionFeedbackOptional = compositionFeedbackDao.getByPK(userCompositionOptional.get().getCortageId(each));
                    if (compositionFeedbackOptional.isPresent()) {

                        Optional<Composition> compositionOptional = compositionDao.getByPK(compositionIdList.get(each));
                        if (compositionOptional.isPresent()) {
                            CompositionFeedbackDto compositionFeedbackDto = new CompositionFeedbackDto();
                            compositionFeedbackDto.setCompositionName(compositionOptional.get().getName());
                            compositionFeedbackDto.setDescription(compositionFeedbackOptional.get().getFeedback());
                            compositionFeedbackDto.setTimestamp(compositionFeedbackOptional.get().getTimestamp());

                            compositionFeedbackDtoList.add(compositionFeedbackDto);
                        }
                    }
                }
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return compositionFeedbackDtoList;
    }

}
