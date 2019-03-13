package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.UserComposition;

import java.util.List;
import java.util.Optional;

public interface CompositionDaoExtended extends GenericDao<Composition, Integer> {

    @AutoConnection
    List<Composition> getListByPK(List<Integer> idList) throws DaoException;


    @AutoConnection
    Optional<Composition> getByName(String name) throws DaoException;
}
