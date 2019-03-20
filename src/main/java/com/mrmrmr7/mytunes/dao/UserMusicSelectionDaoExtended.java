package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserMusicSelection;

import java.util.List;
import java.util.Optional;

public interface UserMusicSelectionDaoExtended {
    @AutoConnection
    List<Integer> getCortageIdByMusicSelectionId(Integer id) throws DaoException;

    Optional<UserMusicSelection> getByCortagePK(Integer i) throws DaoException;
}
