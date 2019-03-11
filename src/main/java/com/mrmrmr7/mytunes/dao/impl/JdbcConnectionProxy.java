package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class JdbcConnectionProxy implements InvocationHandler {
    private final Connection connection;

    public JdbcConnectionProxy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.getName().equals("close")) {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
            return null;
        } else {
            return method.invoke(connection, objects);
        }
    }
}
