package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.service.DBConnectionService;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;
import com.mrmrmr7.mytunes.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionDAO extends AbstractJDBCDAO<Composition, Integer> implements GenericDAO<Composition, Integer> {

    public CompositionDAO() {
    }

    @Override
    public Optional<Composition> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setComposition(resultSet));
            }
        }
    }

    public List<Composition> getByPKList(List<Integer> idList) {

        List<Composition> compositionList = new ArrayList<>();

        idList.forEach(s -> {
            try {
                getByPK(s).ifPresent(compositionList::add);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return compositionList;
    }

    @Override
    public List<Composition> getAll() throws SQLException {

        List<Composition> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.COMPOSITION)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setComposition(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(Composition object) throws SQLException {

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
    public void update(Composition object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Composition object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Composition object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Composition object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getPrice());
        preparedStatement.setString(++i, object.getName());
        preparedStatement.setInt(++i, object.getAlbum_id());
        preparedStatement.setInt(++i, object.getYear());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.COMPOSITION.getValue() +
                "(PRICE, NAME, ALBUM_ID, YEAR) " +
                "VALUES " +
                "(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.COMPOSITION.getValue() + " SET " +
                "PRICE=?, NAME=?, " +
                "ALBUM_ID=? " +
                "WHERE ID=?";
    }
}
