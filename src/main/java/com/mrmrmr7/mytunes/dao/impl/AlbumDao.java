package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AlbumDaoExtended;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Album;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
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
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1001));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1002));
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
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1003));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1004));
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
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1005));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1006));
        }

        return userList;
    }

    private PreparedStatement prepareStatementForGetByAuthorId(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByAuthorIdQuery(Table.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }


    @AutoConnection
    private PreparedStatement prepareStatementForGetByName(String name) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByNameQuery(Table.ALBUM));
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByNameQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE NAME=?";
    }

    @AutoConnection
    @Override
    public List<Album> getAll() throws DaoException {

        List<Album> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.ALBUM)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toAlbum(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1007));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1008));
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Album object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1009));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1010));
        }
    }

    @AutoConnection
    @Override
    public void update(Album object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1011));
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

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Album object) throws SQLException {

        int i = 0;
        preparedStatement.setLong(++i, object.getPrice());
        preparedStatement.setString(++i, object.getDescription());
        preparedStatement.setString(++i, object.getName());
        preparedStatement.setInt(++i, object.getAuthor_id());
        preparedStatement.setInt(++i, object.getGenre_id());
        preparedStatement.setInt(++i, object.getYear());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    protected String getSelectQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    protected String getSelectByAuthorIdQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE AUTHOR_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.ALBUM.getValue() +
                "(PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR) " +
                "VALUES " +
                "(?,?,?,?,?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.ALBUM.getValue() + " SET " +
                "PRICE=?, " +
                "NAME=?, " +
                "DESCRIPTION=?, " +
                "AUTHOR_ID=?, " +
                "GENRE_ID=?, " +
                "YEAR=? " +
                "WHERE ID=?";
    }
}
