package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.MusicSelectionFeedbackDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicSelectionFeedbackService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

public class MusicSelectionFeedbackServiceImpl implements MusicSelectionFeedbackService {
    @Override
    public List<MusicSelectionFeedbackDto> getFeedbackDtoByMusicSelectionId(HttpServletRequest request) throws ServiceException {
        List<MusicSelectionFeedbackDto> musicSelectionFeedbackDtoList = new ArrayList<>();
        String musicSelectionName = request.getParameter("musicSelectionName");
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<MusicSelection, Integer> musicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelection.class);
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionFeedback, Integer> musicSelectionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionFeedback.class);

            transactionManager.begin(musicSelectionDao, userMusicSelectionDao, musicSelectionFeedbackDao, userDao);

            Optional<MusicSelection> musicSelectionOptional = ((MusicSelectionDaoExtended) musicSelectionDao).getByName(musicSelectionName);
            if (musicSelectionOptional.isPresent()) {
                List<Integer> musicSelectionCortageIdList = ((UserMusicSelectionDaoExtended)userMusicSelectionDao).getCortageIdByMusicSelectionId(musicSelectionOptional.get().getId());

                for (Integer i : musicSelectionCortageIdList) {
                    Optional<MusicSelectionFeedback> musicSelectionFeedbackOptional = musicSelectionFeedbackDao.getByPK(i);
                    Optional<UserMusicSelection> userMusicSelectionOptional = ((UserMusicSelectionDaoExtended)userMusicSelectionDao).getByCortagePK(i);

                    if (userMusicSelectionOptional.isPresent()) {
                        Integer userId = userMusicSelectionOptional.get().getId();
                        Integer musicSelectionId = userMusicSelectionOptional.get().getMusicSelectionId(0);

                        if (musicSelectionFeedbackOptional.isPresent()) {
                            MusicSelectionFeedbackDto musicSelectionFeedbackDto = new MusicSelectionFeedbackDto();
                            musicSelectionFeedbackDto.setDescription(musicSelectionFeedbackOptional.get().getFeedback());
                            musicSelectionFeedbackDto.setTimestamp(musicSelectionFeedbackOptional.get().getTimestamp());

                            Optional<User> userOptional = userDao.getByPK(userId);
                            userOptional.ifPresent(user -> musicSelectionFeedbackDto.setUserName(user.getLogin()));
                            musicSelectionFeedbackDtoList.add(musicSelectionFeedbackDto);
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
        return musicSelectionFeedbackDtoList;
    }

    @Override
    public boolean addMusicSelectionFeedback(HttpServletRequest request) throws ServiceException {
        if (request == null) {
            return false;
        }

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionInfo, Integer> musicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);
            GenericDao<MusicSelectionFeedback, Integer> musicSelectionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionFeedback.class);

            transactionManager.begin(userMusicSelectionDao, musicSelectionDao, musicSelectionFeedbackDao);

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userMusicSelectionOptional.isPresent()) {
                String musicSelectionInfoName = request.getParameter("musicSelectionName");
                Optional<MusicSelectionInfo> musicSelectionOptional = ((MusicSelectionInfoDaoExtended)musicSelectionDao).getByName(musicSelectionInfoName);

                if(musicSelectionOptional.isPresent()) {
                    if (userMusicSelectionOptional.get().getMusicSelectionIdList().contains(musicSelectionOptional.get().getId())) {
                        MusicSelectionFeedback musicSelectionFeedback = new MusicSelectionFeedback();
                        int musicSelectionId = userMusicSelectionOptional.get().getMusicSelectionIdList().indexOf(musicSelectionOptional.get().getId());
                        int cortageId = userMusicSelectionOptional.get().getCortageId(musicSelectionId);
                        musicSelectionFeedback.setId(cortageId);
                        musicSelectionFeedback.setFeedback(request.getParameter("musicSelectionFeedback"));
                        musicSelectionFeedback.setTimestamp(new Timestamp(new Date().getTime()));

                        musicSelectionFeedbackDao.insert(musicSelectionFeedback);

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
    public List<MusicSelectionFeedbackDto> getUserMusicSelectionFeedbackList(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        List<MusicSelectionFeedbackDto> musicSelectionFeedbackDtoList = new ArrayList<>();


        TransactionManager transactionManager = new TransactionManagerImpl();
        try {

            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionFeedback, Integer> musicSelectionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionFeedback.class);
            GenericDao<MusicSelectionInfo, Integer> musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);

            transactionManager.begin(userMusicSelectionDao, musicSelectionFeedbackDao, musicSelectionInfoDao);

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userMusicSelectionOptional.isPresent()) {
                List<Integer> cortageIdList = userMusicSelectionOptional.get().getCortageIdList();
                List<Integer> musicSelectionIdList = userMusicSelectionOptional.get().getMusicSelectionIdList();

                for (int each = 0; each < cortageIdList.size(); each++) {

                    Optional<MusicSelectionFeedback> musicSelectionFeedbackOptional = musicSelectionFeedbackDao.getByPK(userMusicSelectionOptional.get().getCortageId(each));
                    if (musicSelectionFeedbackOptional.isPresent()) {

                        Optional<MusicSelectionInfo> musicSelectionOptional = musicSelectionInfoDao.getByPK(musicSelectionIdList.get(each));

                        if (musicSelectionOptional.isPresent()) {
                            MusicSelectionFeedbackDto musicSelectionFeedbackDto = new MusicSelectionFeedbackDto();
                            musicSelectionFeedbackDto.setMusicSelectionName(musicSelectionOptional.get().getName());
                            musicSelectionFeedbackDto.setDescription(musicSelectionFeedbackOptional.get().getFeedback());
                            musicSelectionFeedbackDto.setTimestamp(musicSelectionFeedbackOptional.get().getTimestamp());

                            musicSelectionFeedbackDtoList.add(musicSelectionFeedbackDto);
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

        return musicSelectionFeedbackDtoList;
    }

}
