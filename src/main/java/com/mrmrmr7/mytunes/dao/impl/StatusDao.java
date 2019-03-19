package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDao extends AbstractJdbcDao<Status, Byte> implements GenericDao<Status, Byte> {

    public StatusDao() {
    }

    @AutoConnection
    @Override
    public Optional<Status> getByPK(Byte id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toStatus(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.11.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.11.2");
        }
    }

    @AutoConnection
    @Override
    public List<Status> getAll() throws DaoException {

        List<Status> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.STATUS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toStatus(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.11.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.11.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Status object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.11.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Byte id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.11.6");
        }
    }

    @AutoConnection
    @Override
    public void update(Status object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.11.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Status object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Status object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Byte id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.STATUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Byte id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.STATUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Status object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getStatus());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.STATUS.getValue() +
                "(STATUS) " +
                "VALUES " +
                "(?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.STATUS.getValue() + " SET " +
                "STATUS=? " +
                "WHERE ID=?";
    }
}
