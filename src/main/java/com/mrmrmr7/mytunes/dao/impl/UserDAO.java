package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.DBConnectionService;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;
import com.mrmrmr7.mytunes.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractJDBCDAO<User, Integer> implements GenericDAO<User, Integer> {

    public UserDAO() {
    }

    @Override
    public Optional<User> getByPK(Integer id) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();

                return Optional.of(resultSetCompiller.setUser(resultSet));
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {

        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.USER)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setUser(resultSet));
                }
            }
        }
        return userList;
    }

    @Override
    public void insert(User object) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
                    preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());
        preparedStatement.setInt(11, object.getId());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.USER));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, User object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getLOGIN());
        preparedStatement.setString(++i, object.getPassword());
        preparedStatement.setString(++i, object.getFirst_name());
        preparedStatement.setString(++i, object.getSecond_name());
        preparedStatement.setDate(++i, object.getRegister_data());
        preparedStatement.setInt(++i, object.getSale());
        preparedStatement.setLong(++i, object.getBalance());
        preparedStatement.setByte(++i, object.getRole_id());
        preparedStatement.setByte(++i, object.getStatus_id());
        preparedStatement.setString(++i, object.getEmail());
        return preparedStatement;
    }

    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + TableName.USER.getValue() +
                "(LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) " +
                "VALUES " +
                "(?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + TableName.USER.getValue() + " SET " +
                "LOGIN=?, PASSWORD=?, " +
                "FIRST_NAME=?, SECOND_NAME=?, " +
                "REGISTER_DATE=?, SALE=?, " +
                "BALANCE=?, ROLE_ID=?, " +
                "STATUS_ID=?, EMAIL=? WHERE ID=?";
    }
}
