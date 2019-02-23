package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.ConnectionPool;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionService {
    private static ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.JDBC);

    DBConnectionService() {
    }

    public static Connection getConnection() throws ServiceException {
        try {
            return connectionPool.getConnection();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new ServiceException("Impossible to get connection");
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
