package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPool;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import java.text.MessageFormat;
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
            default: throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1048));
        }
    }
}
