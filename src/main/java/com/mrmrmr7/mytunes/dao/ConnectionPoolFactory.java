package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.impl.JDBCConnectionPool;

import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolFactory {
    private final static ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();

    private final Properties properties = new Properties();

    public static ConnectionPoolFactory getInstance() {
        return INSTANCE;
    }

    private ConnectionPoolFactory() {
    }

    public ConnectionPool getConnectionPool(ConnectionPoolType type) {
        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (type) {
            case JDBC: return new JDBCConnectionPool(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"),
                    properties.getProperty("driver")
            );
            default:
                return null;
        }
    }
}
