package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.MusicSelectionFeedbackDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicSelectionFeedbackDtoService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionFeedbackDtoServiceImpl implements MusicSelectionFeedbackDtoService {
    @Override
    public List<MusicSelectionFeedbackDto> getFeedbackByMusicSelectionId(HttpServletRequest request) throws ServiceException {
        List<MusicSelectionFeedbackDto> musicSelectionFeedbackDtoList = new ArrayList<>();
        String musicSelectionName = request.getParameter("musicSelectionName");

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<MusicSelectionInfo, Integer> musicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionFeedback, Integer> musicSelectionFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionFeedback.class);

            transactionManager.begin(musicSelectionDao, userMusicSelectionDao, musicSelectionFeedbackDao, userDao);

            Optional<MusicSelectionInfo> musicSelectionOptional = ((MusicSelectionInfoDaoExtended) musicSelectionDao).getByName(musicSelectionName);
            if (musicSelectionOptional.isPresent()) {
                List<Integer> feedbackIdList = ((UserMusicSelectionDaoExtended)userMusicSelectionDao).getCortageIdByMusicSelectionId(musicSelectionOptional.get().getId());

                for (Integer i : feedbackIdList) {
                    Optional<MusicSelectionFeedback> musicSelectionFeedbackOptional = musicSelectionFeedbackDao.getByPK(i);
                    Optional<UserMusicSelection> userMusicSelectionOptional = ((UserMusicSelectionDaoExtended) userMusicSelectionDao).getByCortagePK(i);
                    if (userMusicSelectionOptional.isPresent()) {
                        Integer userId = userMusicSelectionOptional.get().getId();
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
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }
        return musicSelectionFeedbackDtoList;
    }
}
