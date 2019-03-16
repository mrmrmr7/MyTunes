package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import java.util.List;

public interface UserAlbumDaoExtended {
    List<Integer> getCortageIdByAlbumId(Integer id) throws DaoException;
}
