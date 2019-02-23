package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.GenericDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.entity.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDAO extends AbstractJDBCDAO<Status, Integer> implements GenericDAO<Status, Integer> {

    public StatusDAO() {
    }

    @Override
    public Optional<Status> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setStatus(resultSet));
            }
        }
    }

    @Override
    public List<Status> getAll() throws SQLException {

        List<Status> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.STATUS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setStatus(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(Status object) throws SQLException {

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
    public void update(Status object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Status object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Status object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.STATUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.STATUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Status object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getStatus());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.STATUS.getValue() +
                "(STATUS) " +
                "VALUES " +
                "(?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.STATUS.getValue() + " SET " +
                "STATUS=? " +
                "WHERE ID=?";
    }
}
