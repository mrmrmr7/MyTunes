package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import java.util.List;

public interface UserMusicSelectionDaoExtended {
    @AutoConnection
    List<Integer> getCortageIdByMusicSelectionId(Integer id) throws DaoException;
}
