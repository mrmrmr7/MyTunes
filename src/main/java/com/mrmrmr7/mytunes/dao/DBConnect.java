package com.mrmrmr7.mytunes.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private final static Logger logger = LogManager.getLogger(DBConnect.class);
    private final String JDBC_URL;
    private final String USER;
    private final String PASSWORD;

    DBConnect(String aJDBCUrl, String aUser, String aPassword, String aDriverclass) {
        this.JDBC_URL = aJDBCUrl;
        this.USER = aUser;
        this.PASSWORD = aPassword;
        initDriver(aDriverclass);
    }

    public Connection getConnection() throws DAOException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException("Exception while getting connection: ",e);
        }

        return connection;
    }

    private void initDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new IllegalStateException("Driver cannot be found", e);
        }
    }
}
