package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.exception.DAOException;

import java.sql.Connection;

public interface TransactionManager {
    void setConnectionWithReflection(Object dao, Connection connection) throws DAOException;

    void begin(GenericDao... daos) throws DAOException;

    void end() throws DAOException;

    void commit() throws DAOException;

    void rollBack() throws DAOException;
}
