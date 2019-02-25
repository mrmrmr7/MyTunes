package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.GenericDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.entity.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDAO extends AbstractJDBCDAO<Album, Integer> {

    public AlbumDAO() {

    }

    @Override
    public Optional<Album> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setAlbum(resultSet));
            }
        }
    }

    @Override
    public List<Album> getAll() throws SQLException {

        List<Album> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.ALBUM)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setAlbum(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(Album object) throws SQLException {

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
    public void update(Album object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Album object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Album object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(
                connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(5, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Album object) throws SQLException {

        int i = 0;
        preparedStatement.setLong(++i, object.getPrice());
        preparedStatement.setString(++i, object.getDescription());
        preparedStatement.setInt(++i, object.getAuthor_id());
        preparedStatement.setInt(++i, object.getGenre_id());
        return preparedStatement;
    }

    @Override
    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE ID=?";
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.ALBUM.getValue() +
                "(PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) " +
                "VALUES " +
                "(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.ALBUM.getValue() + " SET " +
                "PRICE=?, " +
                "DESCRIPTION=?, " +
                "AUTHOR_ID=?, " +
                "GENRE_ID=? " +
                "WHERE ID=?";
    }
}
