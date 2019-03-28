package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionManagerImpl implements TransactionManager {
    private Connection singleConnection;
    private List<GenericDao> genericDaoList = new ArrayList<>();

    @Override
    public void begin(GenericDao... daos) throws DaoException {
        ConnectionPool connectionPool = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.MYSQL);

        singleConnection = connectionPool.getConnection();

        try {
            singleConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1098));
        }

        for (GenericDao dao : daos) {
            genericDaoList.add(dao);
            setConnectionWithReflection(dao, singleConnection);
        }
    }

    @Override
    public void end() throws DaoException {
        try {
            singleConnection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1099));
        }

        try {
            ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .releaseConnection(this.singleConnection);
        } catch (DaoException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1100));
        }

        for (GenericDao genericDAO : genericDaoList) {
            removeConnectionWithReflection(genericDAO);
        }
    }

    @Override
    public void commit() throws DaoException {
        try {
            singleConnection.commit();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1101));
        }
    }

    @Override
    public void rollBack() throws DaoException {
        try {
            singleConnection.rollback();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1102));
        }
    }

    public static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1103));
        }

        try {
            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1104));
        } catch (IllegalAccessException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1105));
        }
    }

    private static void removeConnectionWithReflection(Object dao) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ");
        }
    }

}
