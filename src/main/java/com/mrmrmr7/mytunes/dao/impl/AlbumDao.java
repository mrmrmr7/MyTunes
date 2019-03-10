package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDao extends AbstractJdbcDao<Album, Integer> {
    
    @Override
    public Optional<Album> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setAlbum(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.1.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.2");
        }
    }

    @Override
    public List<Album> getAll() throws DaoException {

        List<Album> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.ALBUM)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setAlbum(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.1.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.4");
        }

        return userList;
    }

    @Override
    public void insert(Album object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.5");
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.6");
        }
    }

    @Override
    public void update(Album object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.7");
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
