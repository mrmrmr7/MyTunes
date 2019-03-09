package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.service.TransactionManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManagerImpl implements TransactionManager {
    private Connection singleConnection;
    private List<GenericDao> genericDaoList = new ArrayList<>();

    @Override
    public void begin(GenericDao... daos) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.MYSQL);

        singleConnection = connectionPool.getConnection();

        try {
            singleConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        for (GenericDao dao : daos) {
            genericDaoList.add(dao);
            setConnectionWithReflection(dao, singleConnection);
        }
    }

    @Override
    public void end() {
        try {
            singleConnection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("impossible to set autocommit: false");
        }

        for (GenericDao genericDAO : genericDaoList) {
            try {
                removeConnectionWithReflection(genericDAO);
            } catch (DAOException e) {
                System.out.println("impossible to close connection in: " + genericDAO);
            }
        }
    }

    @Override
    public void commit() {
        try {
            singleConnection.commit();
        } catch (SQLException e) {
            System.out.println("impossible to commit");
        }
    }

    @Override
    public void rollBack() {
        try {
            singleConnection.rollback();
        } catch (SQLException e) {
            System.out.println("impossible to rollBack");
        }
    }

    @Override
    public void setConnectionWithReflection(Object dao, Connection connection) throws DAOException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DAOException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DAOException("Failed to set connection for transactional DAO. ");
        }
    }

    private static void removeConnectionWithReflection(Object dao) throws DAOException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DAOException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DAOException("Failed to set connection for transactional DAO. ");
        }
    }

}
