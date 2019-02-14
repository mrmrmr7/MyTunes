package com.mrmrmr7.mytunes.dao.impl;


import com.mrmrmr7.mytunes.dao.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCConnection;

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
    private final String JDBC_URL;
    private final String USER;
    private final String PASSWORD;
    private final int POOL_CAPACITY = 8;
    private static AtomicInteger createdConnectionCount = new AtomicInteger(0);
    private static Deque<Connection> pool = new ArrayDeque<>();
    private Lock lockForOpen = new ReentrantLock();
    private static Lock lockForClose = new ReentrantLock();
    private Semaphore semaphore;

    public JDBCConnectionPool(String aJDBCUrl, String aUser, String aPassword, String aDriverclass) {
        this.JDBC_URL = aJDBCUrl;
        this.USER = aUser;
        this.PASSWORD = aPassword;
        semaphore = new Semaphore(POOL_CAPACITY);
        initDriver(aDriverclass);
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        semaphore.acquire();
        lockForOpen.lock();
        System.out.println("lol ");

        try {
            if (createdConnectionCount.get() < POOL_CAPACITY) {
                Connection connection;
                connection = (Connection) Proxy.newProxyInstance(
                        JDBCConnection.class.getClassLoader(),
                        JDBCConnection.class.getInterfaces(),
                        new JDBCConnectionProxy(DriverManager.
                                getConnection(JDBC_URL, USER, PASSWORD)));
                pool.push(connection);
                createdConnectionCount.incrementAndGet();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        Connection connection = pool.poll();

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

    public static void releaseConnection(Connection connection) {
        lockForClose.lock();
        System.out.println("close");
        pool.push(connection);
        lockForClose.lock();
    }
}
