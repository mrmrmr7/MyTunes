package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import java.io.Serializable;

public interface DaoFactory {

    <T extends Identified<PK>, PK extends Number> GenericDao<T, PK> getDao(Class<T> entityClass) throws DaoException;
}
