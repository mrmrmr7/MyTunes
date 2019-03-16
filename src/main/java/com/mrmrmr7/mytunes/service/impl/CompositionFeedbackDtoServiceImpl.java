package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.CompositionDaoExtended;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.UserCompositionDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserComposition;
import com.mrmrmr7.mytunes.service.CompositionFeedbackDtoService;
import com.mrmrmr7.mytunes.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionFeedbackDtoServiceImpl implements CompositionFeedbackDtoService {
    @Override
    public List<CompositionFeedbackDto> getFeedbackByCompositionId(HttpServletRequest request) throws ServiceException {
        List<CompositionFeedbackDto> compositionFeedbackDtoList = new ArrayList<>();
        String compositionName = request.getParameter("compositionName");
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);
            GenericDao<CompositionFeedback, Integer> compositionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(CompositionFeedback.class);

            transactionManager.begin(compositionDao, userCompositionDao, compositionFeedbackDao);

            Optional<Composition> compositionOptional = ((CompositionDaoExtended) compositionDao).getByName(compositionName);
            if (compositionOptional.isPresent()) {
                List<Integer> feedbackIdList = ((UserCompositionDaoExtended)userCompositionDao).getCortageIdByCompositionId(compositionOptional.get().getId());

                for (Integer i : feedbackIdList) {
                    Optional<CompositionFeedback> compositionFeedbackOptional = compositionFeedbackDao.getByPK(i);
                    Integer userId = userCompositionDao.getByPK(i).get().getId();
                    if (compositionFeedbackOptional.isPresent()) {
                        CompositionFeedbackDto compositionFeedbackDto = new CompositionFeedbackDto();
                        compositionFeedbackDto.setDescription(compositionFeedbackOptional.get().getFeedback());
                        compositionFeedbackDto.setTimestamp(compositionFeedbackOptional.get().getTimestamp());

                        Optional<User> userOptional = userDao.getByPK(userId);
                        compositionFeedbackDto.setUserName(userOptional.get().getLogin());
                        compositionFeedbackDtoList.add(compositionFeedbackDto);
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
}
