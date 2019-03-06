package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private Connection singleConnection;
    private List<GenericDAO> genericDAOList = new ArrayList<>();

    public void begin(GenericDAO ... daos) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.MYSQL);

        singleConnection = connectionPool.getConnection();

        try {
            singleConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        for (GenericDAO dao : daos) {
            genericDAOList.add(dao);
            setConnectionWithReflection(dao, singleConnection);
        }
    }

    public void end() {
        try {
            singleConnection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("impossible to set autocommit: false");
        }

        for (GenericDAO genericDAO : genericDAOList) {
            try {
                removeConnectionWithReflection(genericDAO);
            } catch (DAOException e) {
                System.out.println("impossible to close connection in: " + genericDAO);
            }
        }
    }

    public void commit() {
        try {
            singleConnection.commit();
        } catch (SQLException e) {
            System.out.println("impossible to commit");
        }
    }

    public void rollBack() {
        try {
            singleConnection.rollback();
        } catch (SQLException e) {
            System.out.println("impossible to rollBack");
        }
    }

    private static void setConnectionWithReflection(Object dao, Connection connection) throws DAOException {
        if (!(dao instanceof AbstractJDBCDAO)) {
            throw new DAOException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJDBCDAO.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DAOException("Failed to set connection for transactional DAO. ");
        }
    }

    private static void removeConnectionWithReflection(Object dao) throws DAOException {
        if (!(dao instanceof AbstractJDBCDAO)) {
            throw new DAOException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJDBCDAO.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DAOException("Failed to set connection for transactional DAO. ");
        }
    }

}
