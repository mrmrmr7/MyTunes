package com.mrmrmr7.mytunes.dao;


import com.mrmrmr7.mytunes.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCConnection;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JDBCConnectionPool implements ConnectionPool {
    private final static Logger logger = LogManager.getLogger(JDBCConnectionPool.class);
    private final static JDBCConnectionPool INSTANCE = new JDBCConnectionPool();
    private final String JDBC_URL;
    private final String USER;
    private final String PASSWORD;
    private final int POOL_CAPACITY = 8;
    private static AtomicInteger createdConnectionCount = new AtomicInteger(0);
    private static Deque<Connection> pool = new ArrayDeque<>();
    private Lock lockForOpen = new ReentrantLock();
    private static Lock lockForClose = new ReentrantLock();
    private Semaphore semaphore;

    public static JDBCConnectionPool getInstance() {
        return INSTANCE;
    }

    private JDBCConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/property/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        semaphore = new Semaphore(POOL_CAPACITY, true);
        JDBC_URL = properties.getProperty("url");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
        initDriver(properties.getProperty("driver"));
    }

    @Override
    public Connection getConnection() throws DAOException {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new DAOException("");
        }
        lockForOpen.lock();

        try {
            if (createdConnectionCount.get() < POOL_CAPACITY) {
                Connection connection = (Connection) Proxy.newProxyInstance(
                        JDBCConnection.class.getClassLoader(),
                        JDBCConnection.class.getInterfaces(),
                        new JDBCConnectionProxy(DriverManager.
                                getConnection(JDBC_URL, USER, PASSWORD)));
                pool.addLast(connection);
                createdConnectionCount.incrementAndGet();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        Connection connection = pool.pollLast();

        lockForOpen.unlock();

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

    public void releaseConnection(Connection connection) {
        lockForClose.lock();
        JDBCConnectionPool.pool.push((Connection) Proxy.newProxyInstance(
                JDBCConnection.class.getClassLoader(),
                JDBCConnection.class.getInterfaces(),
                new JDBCConnectionProxy(connection)));
        semaphore.release();
        lockForClose.unlock();
    }
}
