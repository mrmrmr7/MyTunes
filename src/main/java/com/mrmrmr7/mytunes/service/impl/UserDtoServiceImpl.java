package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.*;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserDtoService;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDtoServiceImpl implements UserDtoService {
    @Override
    public UserDto getDtoById(int id) throws ServiceException {
        TransactionManager transactionManager = new TransactionManagerImpl();

        JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();

        try {
            GenericDao<User, Integer> userDao = daoFactory.getTransactionalDao(User.class);
            GenericDao<UserBonus, Integer> userBonusDao = daoFactory.getTransactionalDao(UserBonus.class);
            GenericDao<UserAlbum, Integer> userAlbumDao = daoFactory.getTransactionalDao(UserAlbum.class);
            GenericDao<UserComposition, Integer> userCompositionDao = daoFactory.getTransactionalDao(UserComposition.class);
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = daoFactory.getTransactionalDao(UserMusicSelection.class);
            GenericDao<Status, Byte> statusDao = daoFactory.getTransactionalDao(Status.class);
            GenericDao<Role, Byte> roleDao = daoFactory.getTransactionalDao(Role.class);
            GenericDao<Bonus, Integer> bonusDao = daoFactory.getTransactionalDao(Bonus.class);
            GenericDao<Composition, Integer> compositionDao = daoFactory.getTransactionalDao(Composition.class);
            GenericDao<MusicSelection, Integer> musicSelectionDao = daoFactory.getTransactionalDao(MusicSelection.class);
            GenericDao<Album, Integer> albumDao = daoFactory.getTransactionalDao(Album.class);

            transactionManager.begin(userDao,
                    userAlbumDao,
                    userCompositionDao,
                    userMusicSelectionDao,
                    statusDao,
                    roleDao,
                    compositionDao,
                    musicSelectionDao,
                    albumDao,
                    userBonusDao,
                    bonusDao);

            UserDto userDto;

            Optional<User> userOptional = userDao.getByPK(id);

            if (!userOptional.isPresent()) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
            }

            User user = userOptional.get();
            userDto = new UserDto();
            userDto.setLogin(user.getLogin());
            userDto.setFirst_name(user.getFirstName());
            userDto.setSecond_name(user.getSecondName());
            userDto.setBalance(user.getBalance());
            userDto.setEmail(user.getEmail());

            Optional<Status> statusOptional = statusDao.getByPK(user.getStatusId());

            if (!statusOptional.isPresent()) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
            }

            Status status = statusOptional.get();
            userDto.setStatus(status);

            Optional<Role> roleOptional = roleDao.getByPK(user.getRoleId());

            if (!roleOptional.isPresent()) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
            }

            Role role = roleOptional.get();

            userDto.setRole(role);

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(user.getId());

            if (userCompositionOptional.isPresent()) {
                UserComposition u = userCompositionOptional.get();
                List<Integer> userCompositionIdList = u.getCompositionIdList();
                for (int s : userCompositionIdList) {
                    try {
                        Optional<Composition> compositionOptional = compositionDao.getByPK(s);
                        compositionOptional.ifPresent(userDto::addComposition);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }

                }
            }

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(user.getId());

            if (userAlbumOptional.isPresent()) {
                UserAlbum u = userAlbumOptional.get();
                List<Integer> userAlbumList = u.getAlbumIdList();
                for (int t : userAlbumList) {
                    try {
                        Optional<Album> albumOptional = albumDao.getByPK(t);
                        albumOptional.ifPresent(userDto::addAlbum);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }
                }
            }

            Optional<UserBonus> userBonusOptional = userBonusDao.getByPK(user.getId());

            if (userBonusOptional.isPresent()) {
                UserBonus u = userBonusOptional.get();
                List<Integer> bonusIdList = u.getBonusIdList();
                for (int t : bonusIdList) {
                    try {
                        Optional<Bonus> bonusOptional = bonusDao.getByPK(t);
                        bonusOptional.ifPresent(userDto::addBonus);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }
                }
            }

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(user.getId());

            if (userMusicSelectionOptional.isPresent()) {
                UserMusicSelection u = userMusicSelectionOptional.get();
                List<Integer> musicSelectionList = u.getMusicSelectionIdList();
                for (int t : musicSelectionList) {
                    try {
                        Optional<MusicSelection> musicSelectionOptional = musicSelectionDao.getByPK(t);
                        musicSelectionOptional.ifPresent(userDto::addMusicSelection);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }
                }
            }

            transactionManager.commit();
            return userDto;
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK + e.getMessage()));
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION + e.getMessage()));
            }
        }
    }

    @Override
    public UserDto getDtoByLogin(String login) throws ServiceException {
        Optional<User> userOptional;
        try {
            userOptional = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA + e.getMessage()));
        }
        if (userOptional.isPresent()) {
            return getDtoById(userOptional.get().getId());
        } else {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
        }
    }

    public void setDtoFromToken(HttpServletRequest httpServletRequest) throws ServiceException {
        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<Cookie> cookieToken;
        Optional<Cookie> cookiePublicKey;

        cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();

        if (!cookieToken.isPresent()) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_TOKEN));
        }

        cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        if(!cookiePublicKey.isPresent()) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_KEY));
        }

        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        UserDto userDto = getDtoById(decodedJWT.getClaim("userId").asInt());
        httpServletRequest.setAttribute("userDto", userDto);
    }

}
