package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDao extends AbstractJdbcDao<Genre, Integer> implements GenericDao<Genre, Integer> {

    public GenreDao() {
    }

    @AutoConnection
    @Override
    public Optional<Genre> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toGenre(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1052));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1053));
        }
    }

    @AutoConnection
    @Override
    public List<Genre> getAll() throws DaoException {

        List<Genre> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.GENRE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toGenre(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1054));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1055));
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Genre object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1056));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1057));
        }
    }

    @AutoConnection
    @Override
    public void update(Genre object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1058));
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Genre object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        preparedStatement.setString(1, object.getGenre());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Genre object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.GENRE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.GENRE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Genre object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getGenre());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.GENRE.getValue() +
                "(GENRE) " +
                "VALUES " +
                "(?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.GENRE.getValue() + " SET " +
                "GENRE=? " +
                "WHERE ID=?";
    }
}
