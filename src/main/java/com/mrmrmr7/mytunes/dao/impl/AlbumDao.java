package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Album;
import com.mysql.cj.xdevapi.SqlDataResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDao extends AbstractJdbcDao<Album, Integer> implements AlbumDaoExtended {

    @AutoConnection
    @Override
    public Optional<Album> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toAlbum(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.1.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.2");
        }
    }

    @AutoConnection
    @Override
    public Optional<Album> getByName(String name) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGetByName(name)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toAlbum(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.1.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.2");
        }
    }

    @AutoConnection
    @Override
    public List<Album> getByAuthorId(Integer id) throws DaoException {

        List<Album> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetByAuthorId(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toAlbum(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.1.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.4");
        }

        return userList;
    }

    private PreparedStatement prepareStatementForGetByAuthorId(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByAuthorIdQuery(TableName.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }


    @AutoConnection
    private PreparedStatement prepareStatementForGetByName(String name) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByNameQuery(TableName.ALBUM));
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByNameQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE NAME=?";
    }

    @AutoConnection
    @Override
    public List<Album> getAll() throws DaoException {

        List<Album> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.ALBUM)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toAlbum(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.1.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.1.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Album object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.6");
        }
    }

    @AutoConnection
    @Override
    public void update(Album object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.1.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Album object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Album object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(
                connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(5, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Album object) throws SQLException {

        int i = 0;
        preparedStatement.setLong(++i, object.getPrice());
        preparedStatement.setString(++i, object.getDescription());
        preparedStatement.setInt(++i, object.getAuthor_id());
        preparedStatement.setInt(++i, object.getGenre_id());
        preparedStatement.setInt(++i, object.getYear());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    protected String getSelectByAuthorIdQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE AUTHOR_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.ALBUM.getValue() +
                "(PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR) " +
                "VALUES " +
                "(?,?,?,?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.ALBUM.getValue() + " SET " +
                "PRICE=?, " +
                "DESCRIPTION=?, " +
                "AUTHOR_ID=?, " +
                "GENRE_ID=?, " +
                "YEAR=? " +
                "WHERE ID=?";
    }
}
