package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDao extends AbstractJdbcDao<Author, Integer> implements GenericDao<Author, Integer> {

    public AuthorDao() {
    }

    @AutoConnection
    @Override
    public Optional<Author> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return Optional.of(resultSetToBean.toAuthor(resultSet));
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1019));
        }
    }

    @AutoConnection
    @Override
    public List<Author> getAll() throws DaoException {

        List<Author> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.AUTHOR)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toAuthor(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1020));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1021));
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Author object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1022));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1023));
        }
    }

    @AutoConnection
    @Override
    public void update(Author object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1024));
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Author object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Author object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4,object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.AUTHOR));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.AUTHOR));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Author object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getFirstName());
        preparedStatement.setString(++i, object.getSecondName());
        preparedStatement.setString(++i, object.getPseudonim());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    public String getInsertQuery() {

        return "INSERT INTO " + Table.AUTHOR.getValue() +
                "(FIRST_NAME, SECOND_NAME, PSEUDONIM) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    @AutoConnection
    public String getUpdateQuery() {

        return "UPDATE " + Table.AUTHOR.getValue() + " SET " +
                "FIRST_NAME=?, SECOND_NAME=?, " +
                "PSEUDONIM=?" +
                "WHERE ID=?";

    }
}
