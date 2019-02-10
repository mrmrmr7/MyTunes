package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPool;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.DBInstance;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.StatementCompiler;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOQuery<T extends DBInstance> {
    private static DAOQuery ourInstance = new DAOQuery();
    private static StatementCompiler queryCompiler = new StatementCompiler();
    private static ResultSetCompiller resultSetCompiller = new ResultSetCompiller();

    public static DAOQuery getInstance() {
        return ourInstance;
    }

    private DAOQuery() {
    }

    public List<User> getByID(int id) throws DAOException {
        List<User> userList = new ArrayList<>();

        ConnectionPool dbConnect = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.JDBC);

        try (Connection connection = dbConnect.getConnection()) {
            PreparedStatement preparedStatement = queryCompiler.select(connection, "*", "USERS", "ID=" + id);

            ResultSet resultSet = preparedStatement.executeQuery();
            userList.addAll(resultSetCompiller.setUser(resultSet));
        } catch (SQLException e) {
            throw new DAOException("Could not connect to DB: ", e);
        } catch (InterruptedException e) {
            throw new DAOException("Impossible to get connection");
        }

        return userList;
    }
}
