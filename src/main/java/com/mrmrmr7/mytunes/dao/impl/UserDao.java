package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractJdbcDao<User, Integer> implements UserDaoExtended {

    public UserDao() {
    }

    @AutoConnection
    @Override
    public Optional<User> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();

                return Optional.of(resultSetToBean.toUser(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.15.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.15.2");
        }
    }

    @AutoConnection
    @Override
    public List<User> getAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.USER)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toUser(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.15.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.15.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(User object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.15.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.15.6");
        }
    }

    @AutoConnection
    @Override
    public void update(User object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
                    preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException("4.15.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        preparedStatement.setString(11, object.getPublicKey());
        preparedStatement.setString(12, object.getPrivateKey());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(User object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());
        preparedStatement.setString(11, object.getPublicKey());
        preparedStatement.setString(12, object.getPrivateKey());
        preparedStatement.setInt(13, object.getId());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.USER));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.USER));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, User object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getLogin());
        preparedStatement.setString(++i, object.getPassword());
        preparedStatement.setString(++i, object.getFirstName());
        preparedStatement.setString(++i, object.getSecondName());
        preparedStatement.setDate(++i, object.getRegisterData());
        preparedStatement.setInt(++i, object.getSale());
        preparedStatement.setLong(++i, object.getBalance());
        preparedStatement.setByte(++i, object.getRoleId());
        preparedStatement.setByte(++i, object.getStatusId());
        preparedStatement.setString(++i, object.getEmail());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + Table.USER.getValue() +
                "(LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL, PUBLIC_KEY, PRIVATE_KEY) " +
                "VALUES " +
                "(?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @AutoConnection
    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + Table.USER.getValue() + " SET " +
                "LOGIN=?, PASSWORD=?, " +
                "FIRST_NAME=?, SECOND_NAME=?, " +
                "REGISTER_DATE=?, SALE=?, " +
                "BALANCE=?, ROLE_ID=?, " +
                "STATUS_ID=?, EMAIL=?, PUBLIC_KEY=?, PRIVATE_KEY=? WHERE ID=?";
    }

    @AutoConnection
    private PreparedStatement prepareStatementForGetByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByLoginQuery(Table.USER));
        preparedStatement.setString(1, login);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByLoginQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE LOGIN=?";
    }

    @AutoConnection
    @Override
    public Optional<User> getByLogin(String login) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGetByLogin(login)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();

                return Optional.of(resultSetToBean.toUser(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.15.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.15.2");
        }
    }

}
