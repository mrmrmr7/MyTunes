package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws DaoException;
    void releaseConnection(Connection connection) throws DaoException;
    void destroyConnectionPool() throws DaoException;
}
