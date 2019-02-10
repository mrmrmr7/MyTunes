package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.JDBCConnectionPool;

public class ConnectionPoolFactory {
    private final static ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();

    public static ConnectionPoolFactory getInstance() {
        return INSTANCE;
    }

    private ConnectionPoolFactory() {
    }

    public ConnectionPool getConnectionPool(ConnectionPoolType type) throws DAOException {
        switch (type) {
            case JDBC: return new JDBCConnectionPool(
                    "jdbc:hsqldb:mem:testdb",
                    "SA",
                    "",
                    "org.hsqldb.jdbc.JDBCDriver"
            );
            default:
                throw new DAOException("No such ConnectionPool: " + type.name());
        }
    }
}
