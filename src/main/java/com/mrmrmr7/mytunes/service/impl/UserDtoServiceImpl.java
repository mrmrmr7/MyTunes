package com.mrmrmr7.mytunes.service.impl;

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
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDtoServiceImpl implements UserDtoService {
    @Override
    public UserDto getDtoById(int id) throws DaoException {
        TransactionManager transactionManager = new TransactionManagerImpl();

        JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();

        GenericDao userDao = daoFactory.getTransactionalDao(User.class);
        GenericDao userBonusDao = daoFactory.getTransactionalDao(UserBonus.class);
        GenericDao userAlbumDao = daoFactory.getTransactionalDao(UserAlbum.class);
        GenericDao userCompositionDao = daoFactory.getTransactionalDao(UserComposition.class);
        GenericDao userMusicSelectionDao = daoFactory.getTransactionalDao(UserMusicSelection.class);
        GenericDao statusDao = daoFactory.getTransactionalDao(Status.class);
        GenericDao roleDao = daoFactory.getTransactionalDao(Role.class);
        GenericDao bonusDao = daoFactory.getTransactionalDao(Bonus.class);
        GenericDao compositionDao = daoFactory.getTransactionalDao(Composition.class);
        GenericDao musicSelectionDao = daoFactory.getTransactionalDao(MusicSelection.class);
        GenericDao albumDao = daoFactory.getTransactionalDao(Album.class);

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
        try {

            Optional<User> userOptional = userDao.getByPK(id);

            if (!userOptional.isPresent()) {
                throw new DaoException("lol");
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
                throw new DaoException("kek");
            }

            Status status = statusOptional.get();
            userDto.setStatus(status);

            Optional<Role> roleOptional = roleDao.getByPK(user.getRoleId());

            if (!roleOptional.isPresent()) {
                throw new DaoException("omg");
            }

            Role role = roleOptional.get();

            userDto.setRole(role);

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(user.getId());

            userCompositionOptional.ifPresent(u -> {
                List<Integer> userCompositionIdList = u.getCompositionIdList();
                userCompositionIdList.forEach(s -> {
                    try {
                        Optional<Composition> compositionOptional = compositionDao.getByPK(s);
                        compositionOptional.ifPresent(userDto::addComposition);
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                });
            });

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(user.getId());

            userAlbumOptional.ifPresent(u -> {
                List<Integer> userAlbumList = u.getAlbumIdList();
                userAlbumList.forEach(t -> {
                    try {
                        Optional<Album> albumOptional = albumDao.getByPK(t);
                        albumOptional.ifPresent(userDto::addAlbum);
                    } catch (DaoException e) {
                        e.setStackTrace(e.getStackTrace());
                    }
                });
            });

            Optional<UserBonus> userBonusOptional = userBonusDao.getByPK(user.getId());
            userBonusOptional.ifPresent(u -> {
                List<Integer> bonusIdList = u.getBonusIdList();
                bonusIdList.forEach(t -> {
                    try {
                        Optional<Bonus> bonusOptional = bonusDao.getByPK(t);
                        bonusOptional.ifPresent(userDto::addBonus);
                    } catch (DaoException e) {
                        e.setStackTrace(e.getStackTrace());
                    }
                });
            });

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(user.getId());

            userMusicSelectionOptional.ifPresent(u -> {
                List<Integer> musicSelectionList = u.getMusicSelectionIdList();
                musicSelectionList.forEach(t -> {
                    try {
                        Optional<MusicSelection> musicSelectionOptional = musicSelectionDao.getByPK(t);
                        musicSelectionOptional.ifPresent(userDto::addMusicSelection);
                    } catch (DaoException e) {
                        e.setStackTrace(e.getStackTrace());
                    }
                });
            });
        } finally {
            transactionManager.commit();
            transactionManager.end();
        }
        return userDto;
    }

    @Override
    public UserDto getDtoByLogin(String login) throws DaoException {
        Optional<User> userOptional = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(login);

        if (!userOptional.isPresent()) {
            throw new DaoException("ke");
        }

        return getDtoById(userOptional.get().getId());
    }

    public void setDtoFromToken(HttpServletRequest httpServletRequest) throws ServiceException {
        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<Cookie> cookieToken;
        Optional<Cookie> cookiePublicKey;

        cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();

        if (!cookieToken.isPresent()) {
            throw new ServiceException("no token");
        }

        cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        if(!cookiePublicKey.isPresent()) {
            throw new ServiceException("no public key");
        }

        PublicKey publicKey = ProtectionUtil.stringToPublicKey(cookiePublicKey.get().getValue());

        Claims claims;

        try {
            claims = Jwts
                    .parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(cookieToken
                            .get()
                            .getValue())
                    .getBody();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        UserDto userDto = new UserDto();

        try {
            userDto = getDtoById(claims.get("userId", Integer.class));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        httpServletRequest.setAttribute("userDto", userDto);
    }

    public void setDtoByLogin(String login, HttpServletRequest request) throws ServiceException {
        try {
            UserDto userDto = getDtoByLogin(login);
            request.setAttribute("role", userDto.getRole().getId());
            request.setAttribute("userDto", userDto);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
