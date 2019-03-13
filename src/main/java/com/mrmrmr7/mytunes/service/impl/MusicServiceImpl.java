package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class MusicServiceImpl implements MusicService {

    @Override
    public boolean buyComposition(HttpServletRequest request) throws ServiceException {
        String token = request.getParameter("token");
        String publicKey = request.getParameter("publicKey");
        String compositionName = request.getParameter("compositionName");

        if (token == null || publicKey == null || compositionName == null) {
            return false;
        }

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);
        //assert claims != null; FIXME узнать что такое assertClaims

        if(claims == null) {
            return false;
        }

        Integer userId = claims.get("userId", Integer.class);

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao userDao;
            GenericDao compositionDao;
            GenericDao userCompositionDao;
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
                throw new ServiceException("no such composition");
            }

            Composition composition = compositionOptional.get();

            if (user.getBalance() < composition.getPrice()) {
                throw new ServiceException("Тoo enough money");
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
                        throw new ServiceException(e.getMessage());
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
            } catch (DaoException e1) {
                throw new ServiceException("impossible to rollBack");
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException("impossible to end transaction");
            }
        }

        return true;
    }

    @Override
    public boolean buyAlbum(HttpServletRequest request) throws ServiceException {
        String token = request.getParameter("token");
        String publicKey = request.getParameter("publicKey");
        String albumName = request.getParameter("albumName");

        if (token == null || publicKey == null || albumName == null) {
            return false;
        }

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);
        //assert claims != null; FIXME узнать что такое assert claims

        if(claims == null) {
            return false;
        }

        Integer userId = claims.get("userId", Integer.class);

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao userDao;
            GenericDao albumDao;
            GenericDao userAlbumDao;
            userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);

            transactionManager.begin(userDao, albumDao, userAlbumDao);

            userOptional = userDao.getByPK(userId);

            if (!userOptional.isPresent()) {
                return false;
            }

            User user = userOptional.get();

            Optional<Album> albumOptional = ((AlbumDaoExtended) albumDao).getByName(albumName);

            if (!albumOptional.isPresent()) {
                throw new ServiceException("no such composition");
            }

            Album album = albumOptional.get();

            if (user.getBalance() < album.getPrice()) {
                throw new ServiceException("Тoo enough money");
            }

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(userId);


            if (userAlbumOptional.isPresent()) {
                UserAlbum userAlbumInBase = userAlbumOptional.get();
                if(!userAlbumInBase.getAlbumIdList().contains(album.getId())) {
                    UserComposition userComposition = new UserComposition(user.getId(), album.getId());
                    try {
                        user.setBalance(user.getBalance() - album.getPrice());
                        userDao.update(user);
                        userAlbumDao.insert(userComposition);
                    } catch (DaoException e) {
                        throw new ServiceException(e.getMessage());
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
            } catch (DaoException e1) {
                throw new ServiceException("impossible to rollBack");
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException("impossible to end transaction");
            }
        }

        return true;
    }

    @Override
    public boolean buyMusicSelection(HttpServletRequest request) throws ServiceException {
        String token = request.getParameter("token");
        String publicKey = request.getParameter("publicKey");
        String musicSelectionName = request.getParameter("musicSelectionName");

        if (token == null || publicKey == null || musicSelectionName == null) {
            return false;
        }

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);
        //assert claims != null; FIXME узнать что такое assertClaims

        if(claims == null) {
            return false;
        }

        Integer userId = claims.get("userId", Integer.class);

        TransactionManager transactionManager = new TransactionManagerImpl();

        try {
            Optional<User> userOptional;

            GenericDao userDao;
            GenericDao musicSelectionDao;
            GenericDao userMusicSelectionDao;
            userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            musicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelection.class);
            userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);

            transactionManager.begin(userDao, musicSelectionDao, userMusicSelectionDao);

            userOptional = userDao.getByPK(userId);

            if (!userOptional.isPresent()) {
                return false;
            }

            User user = userOptional.get();

            Optional<MusicSelection> musicSelectionOptional = ((MusicSelectionDaoExtended) musicSelectionDao).getByName(musicSelectionName);

            if (!musicSelectionOptional.isPresent()) {
                throw new ServiceException("no such musicSelection");
            }

            MusicSelection musicSelection = musicSelectionOptional.get();

            if (user.getBalance() < musicSelection.getPrice()) {
                throw new ServiceException("Тoo enough money");
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
            } catch (DaoException e1) {
                throw new ServiceException("impossible to rollBack");
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException("impossible to end transaction");
            }
        }


        return true;
    }
}
