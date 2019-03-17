package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dao.impl.UserMusicSelectionDao;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.MusicSelectionInfoService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionInfoServiceImpl implements MusicSelectionInfoService {
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

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);

        List<MusicSelectionInfo> realMusicSelectionInfoList = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);
            GenericDao userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);

            transactionManager.begin(musicSelectionInfoDao, userMusicSelectionDao);

            List<MusicSelectionInfo> musicSelectionInfoList = musicSelectionInfoDao.getAll();
            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(claims.get("userId", Integer.class));
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

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);

        List<MusicSelectionInfo> musicSelectionInfoList = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<UserMusicSelection, Integer> userMusicSelectionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserMusicSelection.class);
            GenericDao<MusicSelectionInfo, Integer> musicSelectionInfoDao = JdbcDaoFactory.getInstance().getTransactionalDao(MusicSelectionInfo.class);

            transactionManager.begin(userMusicSelectionDao, musicSelectionInfoDao);

            Optional<UserMusicSelection> userMusicSelectionOptional = userMusicSelectionDao.getByPK(claims.get("userId", Integer.class));
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
