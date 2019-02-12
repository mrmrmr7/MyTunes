package com.mrmrmr7.mytunes.dao.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class JDBCConnectionProxy implements InvocationHandler {
    private final Connection connection;

    public JDBCConnectionProxy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.getName().equals("close")) {
            JDBCConnectionPool.releaseConnection();
        }
        return method.invoke(connection, objects);
    }
}
