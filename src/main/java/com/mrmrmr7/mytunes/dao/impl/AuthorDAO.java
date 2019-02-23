package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Author;
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

public class AuthorDAO extends AbstractJDBCDAO<Author, Integer> implements GenericDAO<Author, Integer> {

    public AuthorDAO() {
    }

    @Override
    public Optional<Author> getByPK(Integer id) throws DAOException, SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return Optional.of(resultSetCompiller.setAuthor(resultSet));
        }
    }

    @Override
    public List<Author> getAll() throws SQLException {

        List<Author> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.AUTHOR);){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setAuthor(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(Author object) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Author object) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Author object) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Author object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4,object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.AUTHOR));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

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
