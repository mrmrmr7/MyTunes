package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao extends AbstractJdbcDao<Role, Byte> implements GenericDao<Role, Byte> {

    public RoleDao() {
    }

    @AutoConnection
    @Override
    public Optional<Role> getByPK(Byte id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toRole(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.10.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.10.2");
        }
    }

    @AutoConnection
    @Override
    public List<Role> getAll() throws DaoException {

        List<Role> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.ROLE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toRole(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.10.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.10.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Role object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Byte id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.6");
        }
    }

    @AutoConnection
    @Override
    public void update(Role object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Role object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Role object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Byte id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.ROLE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Byte id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.ROLE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Role object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getRole());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    public String getInsertQuery() {

        return "INSERT INTO " + Table.ROLE.getValue() +
                "(ROLE) " +
                "VALUES " +
                "(?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.ROLE.getValue() + " SET " +
                "ROLE=? " +
                "WHERE ID=?";
    }
}
