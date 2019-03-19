package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.AlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.UserAlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.AlbumFeedbackDto;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserAlbum;
import com.mrmrmr7.mytunes.service.AlbumFeedbackDtoService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumFeedbackDtoServiceImpl implements AlbumFeedbackDtoService {
    @Override
    public List<AlbumFeedbackDto> getFeedbackByAlbumId(HttpServletRequest request) throws ServiceException {
        List<AlbumFeedbackDto> albumFeedbackDtoList = new ArrayList<>();
        String albumName = request.getParameter("albumName");
        
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);
            GenericDao<AlbumFeedback, Integer> albumFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(AlbumFeedback.class);

            transactionManager.begin(albumDao, userAlbumDao, albumFeedbackDao, userDao);

            Optional<Album> albumOptional = ((AlbumDaoExtended) albumDao).getByName(albumName);
            if (albumOptional.isPresent()) {
                List<Integer> feedbackIdList = ((UserAlbumDaoExtended)userAlbumDao).getCortageIdByAlbumId(albumOptional.get().getId());

                for (Integer i : feedbackIdList) {
                    Optional<AlbumFeedback> albumFeedbackOptional = albumFeedbackDao.getByPK(i);
                    Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(i);
                    if (userAlbumOptional.isPresent()) {
                        Integer userId = userAlbumOptional.get().getId();
                        if (albumFeedbackOptional.isPresent()) {
                            AlbumFeedbackDto albumFeedbackDto = new AlbumFeedbackDto();
                            albumFeedbackDto.setDescription(albumFeedbackOptional.get().getFeedback());
                            albumFeedbackDto.setTimestamp(albumFeedbackOptional.get().getTimestamp());

                            Optional<User> userOptional = userDao.getByPK(userId);
                            userOptional.ifPresent(user -> albumFeedbackDto.setUserName(user.getLogin()));
                            albumFeedbackDtoList.add(albumFeedbackDto);
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
        return albumFeedbackDtoList;
    }
}
