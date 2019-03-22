package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.CompositionDaoExtended;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.MusicSelectionInfoDaoExtended;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.MusicSelectionDao;
import com.mrmrmr7.mytunes.dao.impl.MusicSelectionInfoDao;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicSelectionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.util.*;

public class MusicSelectionServiceImpl implements MusicSelectionService {
    @Override
    public Optional<MusicSelectionInfo> getMusicSelectionInfoByName(String name) throws ServiceException {
        Optional<MusicSelectionInfo> musicSelectionInfoOptional = Optional.empty();
        try {
            musicSelectionInfoOptional = ((MusicSelectionInfoDaoExtended) JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class)).getByName(name);

        } catch (DaoException e) {
            e.printStackTrace();
        }
        return musicSelectionInfoOptional;
    }

    @Override
    public boolean createMusicSelection(HttpServletRequest request) throws ServiceException {

        MusicSelectionInfo musicSelectionInfo = new MusicSelectionInfo();

        int musicSelectionPrice = Integer.valueOf(request.getParameter("musicSelectionPrice"));
        String musicSelectionName = request.getParameter("musicSelectionName");
        String musicSelectionDescription = request.getParameter("musicSelectionDescription");

        musicSelectionInfo.setDescription(musicSelectionDescription);
        musicSelectionInfo.setPrice(musicSelectionPrice);
        musicSelectionInfo.setName(musicSelectionName);

        try {
            JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class).insert(musicSelectionInfo);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }


        return true;
    }

    @Override
    public boolean updateMusicSelection(HttpServletRequest request) throws ServiceException {

        MusicSelectionInfo musicSelectionInfo = new MusicSelectionInfo();

        int musicSelectionId = Integer.valueOf(request.getParameter("musicSelectionId"));
        int musicSelectionPrice = Integer.valueOf(request.getParameter("musicSelectionPrice"));
        String musicSelectionName = request.getParameter("musicSelectionName");
        String musicSelectionDescription = request.getParameter("musicSelectionDescription");

        musicSelectionInfo.setDescription(musicSelectionDescription);
        musicSelectionInfo.setPrice(musicSelectionPrice);
        musicSelectionInfo.setName(musicSelectionName);
        musicSelectionInfo.setId(musicSelectionId);

        try {
            JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class).update(musicSelectionInfo);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }

    @Override
    public boolean insertMusicSelection(HttpServletRequest request) throws ServiceException {
        int musicSelectionPrice = Integer.valueOf(request.getParameter("musicSelectionPrice"));
        String musicSelectionName = request.getParameter("musicSelectionName");
        String musicSelectionDescription = request.getParameter("musicSelectionDescription");

        MusicSelectionInfo musicSelectionInfo = new MusicSelectionInfo();
        musicSelectionInfo.setName(musicSelectionName);
        musicSelectionInfo.setPrice(musicSelectionPrice);
        musicSelectionInfo.setDescription(musicSelectionDescription);

        try {
            JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class).insert(musicSelectionInfo);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }

    @Override
    public boolean addCompositionToMusicSelection(HttpServletRequest request) throws ServiceException {
        MusicSelection musicSelection = new MusicSelection();
        String compositionName = request.getParameter("compositionName");
        String musicSelectionName = request.getParameter("musicSelectionName");

        try {
            Optional<Composition> compositionOptional = ((CompositionDaoExtended) JdbcDaoFactory.getInstance().getDao(Composition.class)).getByName(compositionName);
            musicSelection.setCompositionId(compositionOptional.get().getId());
            Optional<MusicSelectionInfo> musicSelectionOptional = ((MusicSelectionInfoDaoExtended) JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class)).getByName(musicSelectionName);
            musicSelection.setSelection_id(musicSelectionOptional.get().getId());
            JdbcDaoFactory.getInstance().getDao(MusicSelection.class).insert(musicSelection);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }

    @Override
    public List<MusicSelectionInfo> getAllMusicSelectionInfo() throws ServiceException {
        List<MusicSelectionInfo> musicSelectionInfoList = null;
        try {
            musicSelectionInfoList = JdbcDaoFactory.getInstance().getDao(MusicSelectionInfo.class).getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return musicSelectionInfoList;
    }

    @Override
    public List<MusicSelectionInfo> getAllNotUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        List<MusicSelectionInfo> realMusicSelectionInfoList = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);
            GenericDao userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);

            transactionManager.begin(musicSelectionInfoDao, userMusicSelectionDao);

            List<MusicSelectionInfo> musicSelectionInfoList = musicSelectionInfoDao.getAll();
            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(decodedJWT.getClaim("userId").asInt());
            userMusicSelectionOptional.ifPresent(
                    u -> {
                        for (MusicSelectionInfo c : musicSelectionInfoList)
                            if (!u.getMusicSelectionIdList().contains(c.getId())) {
                                realMusicSelectionInfoList.add(c);
                            }
                    }
            );
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                throw new ServiceException(e.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }

        return realMusicSelectionInfoList;
    }

    @Override
    public List<MusicSelectionInfo> getAllUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        List<MusicSelectionInfo> musicSelectionInfoList = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionInfo, Integer> musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);

            transactionManager.begin(userMusicSelectionDao, musicSelectionInfoDao);

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(decodedJWT.getClaim("userId").asInt());
            if (userMusicSelectionOptional.isPresent()) {

                List<Integer> userMusicSelectionIdList = userMusicSelectionOptional.get().getMusicSelectionIdList();
                for (Integer each : userMusicSelectionIdList) {

                    Optional<MusicSelectionInfo> musicSelectionInfoOptional = musicSelectionInfoDao.getByPK(each);
                    if (musicSelectionInfoOptional.isPresent()) {

                        musicSelectionInfoList.add(musicSelectionInfoOptional.get());
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

        return musicSelectionInfoList;
    }
}
