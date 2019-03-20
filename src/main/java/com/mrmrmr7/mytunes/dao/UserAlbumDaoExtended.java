package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserAlbum;
import com.mrmrmr7.mytunes.entity.UserComposition;

import java.util.List;
import java.util.Optional;

public interface UserAlbumDaoExtended {
    List<Integer> getCortageIdByAlbumId(Integer id) throws DaoException;

    Optional<UserAlbum> getByCortagePK(Integer i) throws DaoException;
}
