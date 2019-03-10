package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.security.SecureRandom;

public interface TransactionalDaoFactory {
    <T extends Identified<PK>, PK extends Number> GenericDao<T,PK> getTransactionalDao(Class<T> tClass) throws DaoException;
}
