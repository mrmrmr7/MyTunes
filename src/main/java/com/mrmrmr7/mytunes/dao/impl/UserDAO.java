package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractJDBCDAO<User, Integer> implements GenericDAO<User, Integer> {
    private final static UserDAO ourInstance = new UserDAO();
    private final ResultSetCompiller resultSetCompiller = new ResultSetCompiller();
    private final ConnectionPool dbConnect = ConnectionPoolFactory
            .getInstance()
            .getConnectionPool(ConnectionPoolType.JDBC);

    public static UserDAO getInstance() {
        return ourInstance;
    }

    private UserDAO() {
    }

    @Override
    public Optional<User> getByPK(Integer id) throws DAOException, SQLException {

        try (Connection connection = dbConnect.getConnection()) {
            ResultSet resultSet = prepareStatementForGet(connection, id)
                    .executeQuery();
            resultSet.next();
            return Optional.of(resultSetCompiller.setUser(resultSet));
        } catch (InterruptedException e) {
            throw new DAOException("Impossible to get connection");
        }
    }

    @Override
    public List<User> getAll() throws SQLException {

        List<User> userList = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection()) {
            ResultSet resultSet = prepareStatementForGetAll(connection, TableName.USER).executeQuery();
            while (resultSet.next()) {
                userList
                        .add(resultSetCompiller.setUser(resultSet));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void insert(User object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForInsert(connection, object)
                    .executeUpdate();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForDelete(connection, id)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForUpdate(connection, object)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Connection connection, User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Connection connection, User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Connection connection, Integer id) throws SQLException {
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
        preparedStatement.setInt(++i, object.getId());
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
