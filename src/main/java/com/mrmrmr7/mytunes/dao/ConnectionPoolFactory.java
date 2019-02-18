package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.impl.JDBCConnectionPool;

import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolFactory {
    private final static ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();
    public static ConnectionPoolFactory getInstance() {
        return INSTANCE;
    }

    private ConnectionPoolFactory() {
    }

    public ConnectionPool getConnectionPool(ConnectionPoolType type) {

        switch (type) {
            case JDBC: return JDBCConnectionPool
                    .getInstance();
            default:
                return null;
        }
    }
}
