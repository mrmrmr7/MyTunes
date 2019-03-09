package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;

import java.util.List;

public interface CompositionDaoExtended extends GenericDao<Composition, Integer> {

    List<Composition> getListByPK(List<Integer> idList) throws DAOException;
}
