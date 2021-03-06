package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

public class MusicServiceImpl implements MusicService {

    @Override
    public boolean buyComposition(HttpServletRequest request) throws ServiceException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        String token = cookieToken.get().getValue();
        String publicKey = cookiePublicKey.get().getValue();
        String compositionName = request.getParameter("compositionName");

        if (token == null || publicKey == null || compositionName == null) {
            return false;
        }

        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        Integer userId = decodedJWT.getClaim("userId").asInt();

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao<User, Integer> userDao;
            GenericDao<Composition, Integer> compositionDao;
            GenericDao<UserComposition, Integer> userCompositionDao;

            userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);

            transactionManager.begin(userDao, compositionDao, userCompositionDao);

            userOptional = userDao.getByPK(userId);

            if (!userOptional.isPresent()) {
                return false;
            }

            User user = userOptional.get();

            Optional<Composition> compositionOptional = ((CompositionDaoExtended) compositionDao).getByName(compositionName);

            if (!compositionOptional.isPresent()) {
                return false;
            }

            Composition composition = compositionOptional.get();

            if (user.getBalance() < composition.getPrice()) {
                return false;
            }

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(userId);


            if (userCompositionOptional.isPresent()) {
                UserComposition userCompositionInBase = userCompositionOptional.get();
                if(!userCompositionInBase.getCompositionIdList().contains(composition.getId())) {
                    UserComposition userComposition = new UserComposition(user.getId(), composition.getId());
                    try {
                        user.setBalance(user.getBalance() - composition.getPrice());
                        userDao.update(user);
                        userCompositionDao.insert(userComposition);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
                    }
                }
            } else {
                user.setBalance(user.getBalance() - composition.getPrice());
                userDao.update(user);
                UserComposition userComposition = new UserComposition(user.getId(), composition.getId());
                userCompositionDao.insert(userComposition);
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

        return true;
    }

    @Override
    public boolean buyAlbum(HttpServletRequest request) throws ServiceException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals(RequestDirector.TOKEN.getValue())).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals(RequestDirector.PUBLIC_KEY.getValue())).findFirst();

        String token = cookieToken.get().getValue();
        String publicKey = cookiePublicKey.get().getValue();
        String albumName = request.getParameter("albumName");

        if (token == null || publicKey == null || albumName == null) {
            return false;
        }

        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());


        Integer userId = decodedJWT.getClaim("userId").asInt();

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);


            transactionManager.begin(userDao, albumDao, userAlbumDao);

            userOptional = userDao.getByPK(userId);

            if (!userOptional.isPresent()) {
                return false;
            }

            User user = userOptional.get();

            Optional<Album> albumOptional = ((AlbumDaoExtended) albumDao).getByName(albumName);

            if (!albumOptional.isPresent()) {
                return false;
            }

            Album album = albumOptional.get();

            if (user.getBalance() < album.getPrice()) {
                return false;
            }

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(userId);


            if (userAlbumOptional.isPresent()) {
                UserAlbum userAlbumInBase = userAlbumOptional.get();
                if(!userAlbumInBase.getAlbumIdList().contains(album.getId())) {
                    UserAlbum userAlbum = new UserAlbum(user.getId(), album.getId());
                    try {
                        user.setBalance(user.getBalance() - album.getPrice());
                        userDao.update(user);
                        userAlbumDao.insert(userAlbum);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }
                }
            } else {
                user.setBalance(user.getBalance() - album.getPrice());
                userDao.update(user);
                UserAlbum userAlbum = new UserAlbum(user.getId(), album.getId());
                userAlbumDao.insert(userAlbum);
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

        return true;
    }

    @Override
    public boolean buyMusicSelection(HttpServletRequest request) throws ServiceException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        String token = cookieToken.get().getValue();
        String publicKey = cookiePublicKey.get().getValue();
        String musicSelectionInfoName = request.getParameter("musicSelectionName");

        if (token == null || publicKey == null || musicSelectionInfoName == null) {
            return false;
        }

        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        Integer userId = decodedJWT.getClaim("userId").asInt();

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<MusicSelectionInfo, Integer> musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);

            transactionManager.begin(userDao, musicSelectionInfoDao, userMusicSelectionDao);

            userOptional = userDao.getByPK(userId);

            if (!userOptional.isPresent()) {
                return false;
            }

            User user = userOptional.get();

            Optional<MusicSelectionInfo> musicSelectionOptional = ((MusicSelectionInfoDaoExtended) musicSelectionInfoDao).getByName(musicSelectionInfoName);

            if (!musicSelectionOptional.isPresent()) {
                return false;
            }

            MusicSelectionInfo musicSelection = musicSelectionOptional.get();

            if (user.getBalance() < musicSelection.getPrice()) {
                return false;
            }

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(userId);


            if (userMusicSelectionOptional.isPresent()) {
                UserMusicSelection userMusicSelectionInBase = userMusicSelectionOptional.get();
                if(!userMusicSelectionInBase.getMusicSelectionIdList().contains(musicSelection.getId())) {
                    UserMusicSelection userMusicSelection = new UserMusicSelection(user.getId(), musicSelection.getId());
                    try {
                        user.setBalance(user.getBalance() - musicSelection.getPrice());
                        userDao.update(user);
                        userMusicSelectionDao.insert(userMusicSelection);
                    } catch (DaoException e) {
                        throw new ServiceException(e.getMessage());
                    }
                }
            } else {
                UserMusicSelection userMusicSelection = new UserMusicSelection(user.getId(), musicSelection.getId());
                user.setBalance(user.getBalance() - musicSelection.getPrice());
                userDao.update(user);
                userMusicSelectionDao.insert(userMusicSelection);
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


        return true;
    }
}
