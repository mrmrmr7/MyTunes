package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

public interface TransactionManager {
    @AutoConnection
    void begin(GenericDao... daos) throws DaoException;

    @AutoConnection
    void end() throws DaoException;

    @AutoConnection
    void commit() throws DaoException;

    @AutoConnection
    void rollBack() throws DaoException;
}
