package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDAO extends AbstractJDBCDAO<Author, Integer> implements GenericDAO<Author, Integer> {
    private final static AuthorDAO ourInstance = new AuthorDAO();
    private final ResultSetCompiller resultSetCompiller = new ResultSetCompiller();
    private final ConnectionPool dbConnect = ConnectionPoolFactory
            .getInstance()
            .getConnectionPool(ConnectionPoolType.JDBC);

    public static AuthorDAO getInstance() {
        return ourInstance;
    }

    public AuthorDAO() {
    }

    @Override
    public Optional<Author> getByPK(Integer id) throws DAOException, SQLException {

        try (Connection connection = dbConnect.getConnection()) {
            try (PreparedStatement preparedStatement = prepareStatementForGet(connection, id)){
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return Optional.of(resultSetCompiller.setAuthor(resultSet));
                }
            }
        } catch (InterruptedException e) {
            throw new DAOException("Impossible to get connection");
        }
    }

    @Override
    public List<Author> getAll() throws SQLException {

        List<Author> userList = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection()) {
            try (PreparedStatement preparedStatement = prepareStatementForGetAll(connection, TableName.AUTHOR)){
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        userList
                                .add(resultSetCompiller.setAuthor(resultSet));
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void insert(Author object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            try (PreparedStatement preparedStatement = prepareStatementForInsert(connection, object)) {
                preparedStatement.executeUpdate();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection connection = dbConnect.getConnection()){
            try (PreparedStatement preparedStatement = prepareStatementForDelete(connection, id)){
                preparedStatement.executeUpdate();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Author object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            try (PreparedStatement preparedStatement = prepareStatementForUpdate(connection, object)){
                preparedStatement.executeUpdate();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Connection connection, Author object) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Connection connection, Author object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4,object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.AUTHOR));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.AUTHOR));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Author object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getFirstName());
        preparedStatement.setString(++i, object.getSecondName());
        preparedStatement.setString(++i, object.getPseudonim());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.AUTHOR.getValue() +
                "(FIRST_NAME, SECOND_NAME, PSEUDONIM) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.AUTHOR.getValue() + " SET " +
                "FIRST_NAME=?, SECOND_NAME=?, " +
                "PSEUDONIM=?" +
                "WHERE ID=?";

    }
}
