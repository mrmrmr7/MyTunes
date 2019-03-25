package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.AlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.UserAlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.AlbumFeedbackDto;
import com.mrmrmr7.mytunes.dto.AlbumFeedbackDto;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserAlbum;
import com.mrmrmr7.mytunes.service.AlbumFeedbackService;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

public class AlbumFeedbackServiceImpl implements AlbumFeedbackService {
    @Override
    public List<AlbumFeedbackDto> getFeedbackDtoByAlbumId(HttpServletRequest request) throws ServiceException {
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
                List<Integer> albumCortageIdList = ((UserAlbumDaoExtended)userAlbumDao).getCortageIdByAlbumId(albumOptional.get().getId());

                for (Integer i : albumCortageIdList) {
                    Optional<AlbumFeedback> albumFeedbackOptional = albumFeedbackDao.getByPK(i);
                    Optional<UserAlbum> userAlbumOptional = ((UserAlbumDaoExtended)userAlbumDao).getByCortagePK(i);

                    if (userAlbumOptional.isPresent()) {
                        Integer userId = userAlbumOptional.get().getId();
                        Integer albumId = userAlbumOptional.get().getAlbumId(0);

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

    @Override
    public boolean addAlbumFeedback(HttpServletRequest request) throws ServiceException {
        if (request == null) {
            return false;
        }

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookie.get().getValue());
//        Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<AlbumFeedback, Integer> albumFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(AlbumFeedback.class);

            transactionManager.begin(userAlbumDao, albumDao, albumFeedbackDao);

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userAlbumOptional.isPresent()) {
                String musicSelectionInfoName = request.getParameter("albumName");
                Optional<Album> albumOptional = ((AlbumDaoExtended)albumDao).getByName(musicSelectionInfoName);

                if(albumOptional.isPresent()) {
                    if (userAlbumOptional.get().getAlbumIdList().contains(albumOptional.get().getId())) {
                        AlbumFeedback albumFeedback = new AlbumFeedback();
                        int albumId = userAlbumOptional.get().getAlbumIdList().indexOf(albumOptional.get().getId());
                        int cortageId = userAlbumOptional.get().getCortageId(albumId);
                        albumFeedback.setId(cortageId);
                        albumFeedback.setFeedback(request.getParameter("albumFeedback"));
                        albumFeedback.setTimestamp(new Timestamp(new Date().getTime()));

                        albumFeedbackDao.insert(albumFeedback);

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
    public List<AlbumFeedbackDto> getUserAlbumFeedbackList(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookie.get().getValue());

//        Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);

        List<AlbumFeedbackDto> albumFeedbackDtoList = new ArrayList<>();


        TransactionManager transactionManager = new TransactionManagerImpl();
        try {

            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);
            GenericDao<AlbumFeedback, Integer> albumFeedbackDao = JdbcDaoFactory.getInstance().getTransactionalDao(AlbumFeedback.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);

            transactionManager.begin(userAlbumDao, albumFeedbackDao, albumDao);

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userAlbumOptional.isPresent()) {
                List<Integer> cortageIdList = userAlbumOptional.get().getCortageIdList();
                List<Integer> albumIdList = userAlbumOptional.get().getAlbumIdList();

                for (int each = 0; each < cortageIdList.size(); each++) {

                    Optional<AlbumFeedback> albumFeedbackOptional = albumFeedbackDao.getByPK(userAlbumOptional.get().getCortageId(each));
                    if (albumFeedbackOptional.isPresent()) {

                        Optional<Album> albumOptional = albumDao.getByPK(albumIdList.get(each));
                        if (albumOptional.isPresent()) {
                            AlbumFeedbackDto albumFeedbackDto = new AlbumFeedbackDto();
                            albumFeedbackDto.setAlbumName(albumOptional.get().getName());
                            albumFeedbackDto.setDescription(albumFeedbackOptional.get().getFeedback());
                            albumFeedbackDto.setTimestamp(albumFeedbackOptional.get().getTimestamp());

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

        return albumFeedbackDtoList;
    }

}
