package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.MusicSelection;

import java.util.Optional;

public interface MusicSelectionDaoExtended extends GenericDao<MusicSelection, Integer> {

    @AutoConnection
    Optional<MusicSelection> getByName(String name) throws DaoException;
}
