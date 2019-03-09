package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;

public class ConnectionPoolFactory {
    private final static ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();
    public static ConnectionPoolFactory getInstance() {
        return INSTANCE;
    }

    private ConnectionPoolFactory() {
    }

    public ConnectionPool getConnectionPool(ConnectionPoolType type) throws DAOException {

        switch (type) {
            case MYSQL: return JdbcConnectionPool.getInstance();
            default: throw new DAOException("dao.connectionpool.1");
        }
    }
}
