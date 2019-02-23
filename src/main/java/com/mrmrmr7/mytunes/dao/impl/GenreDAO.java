package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.GenericDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDAO extends AbstractJDBCDAO<Genre, Integer> implements GenericDAO<Genre, Integer> {

    public GenreDAO() {
    }

    @Override
    public Optional<Genre> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setGenre(resultSet));
            }
        }
    }

    @Override
    public List<Genre> getAll() throws SQLException {

        List<Genre> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.GENRE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setGenre(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(Genre object) throws SQLException {

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
    public void update(Genre object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Genre object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Genre object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.GENRE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.GENRE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Genre object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getGenre());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.GENRE.getValue() +
                "(GENRE) " +
                "VALUES " +
                "(?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.GENRE.getValue() + " SET " +
                "GENRE=? " +
                "WHERE ID=?";
    }
}
