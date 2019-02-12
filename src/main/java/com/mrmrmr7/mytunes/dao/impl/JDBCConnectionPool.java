package com.mrmrmr7.mytunes.dao.impl;


import com.mrmrmr7.mytunes.dao.ConnectionPool;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCConnection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JDBCConnectionPool implements ConnectionPool {
    private final static Logger logger = LogManager.getLogger(JDBCConnectionPool.class);
    private final String JDBC_URL;
    private final String USER;
    private final String PASSWORD;
    private final int POOL_CAPACITY = 8;
    public static List<Connection> openConnection = new ArrayList<>();
    public static List<Connection> closedConnection = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    public JDBCConnectionPool(String aJDBCUrl, String aUser, String aPassword, String aDriverclass) {
        this.JDBC_URL = aJDBCUrl;
        this.USER = aUser;
        this.PASSWORD = aPassword;
        initDriver(aDriverclass);
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        Connection connection;
        lock.lock();
        try {
            if (closedConnection.size() + openConnection.size() < POOL_CAPACITY) {
                connection = (Connection) Proxy.newProxyInstance(
                        JDBCConnection.class.getClassLoader(),
                        JDBCConnection.class.getInterfaces(),
                        new JDBCConnectionProxy(DriverManager.
                                getConnection(JDBC_URL, USER, PASSWORD)));
                closedConnection.add(connection);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        while (closedConnection.isEmpty()) {
            Thread.sleep(10L);
        }

        connection = closedConnection.remove(0);
        openConnection.add(connection);

        lock.unlock();

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

    public static synchronized void releaseConnection() {
        closedConnection.add(openConnection.remove(0));
    }
}
