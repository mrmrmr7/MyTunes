package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.Composition;

import java.util.List;
import java.util.Optional;

public interface AlbumDaoExtended extends GenericDao<Album, Integer> {

    @AutoConnection
    Optional<Album> getByName(String name) throws DaoException;

    @AutoConnection
    List<Album> getByAuthorId(Integer id) throws DaoException;
}
