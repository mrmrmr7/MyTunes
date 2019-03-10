package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolFactory {
    private static ConnectionPoolFactory INSTANCE;
    private static Lock lock = new ReentrantLock();

    private ConnectionPoolFactory() {
    }

    public static ConnectionPoolFactory getInstance() {
        lock.lock();

        try {
            if (INSTANCE == null) {
                INSTANCE = new ConnectionPoolFactory();
            }
        } finally {
            lock.unlock();
        }

        return INSTANCE;
    }

    public ConnectionPool getConnectionPool(ConnectionPoolType type) throws DaoException {

        switch (type) {
            case MYSQL: return ConnectionPoolImpl.getInstance();
            default: throw new DaoException("dao.connectionpool.1");
        }
    }
}
