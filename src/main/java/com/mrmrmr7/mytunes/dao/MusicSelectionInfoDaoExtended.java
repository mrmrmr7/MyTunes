package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;

import java.util.Optional;

public interface MusicSelectionInfoDaoExtended extends GenericDao<MusicSelectionInfo, Integer> {

    Optional<MusicSelectionInfo> getByName(String name) throws DaoException;
}
