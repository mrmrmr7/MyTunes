package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionFeedbackDAO extends AbstractJDBCDAO<CompositionFeedback, Integer> implements GenericDAO<CompositionFeedback, Integer> {

    public CompositionFeedbackDAO() {
    }

    @Override
    public Optional<CompositionFeedback> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return Optional.of(resultSetCompiller.setCompositionFeedback(resultSet));
        }
    }

    @Override
    public List<CompositionFeedback> getAll() throws SQLException {

        List<CompositionFeedback> compositionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.COMPOSITION_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    compositionFeedbackList
                            .add(resultSetCompiller.setCompositionFeedback(resultSet));
                }
            }
        }

        return compositionFeedbackList;
    }

    @Override
    public void insert(CompositionFeedback object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(CompositionFeedback object) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.COMPOSITION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.COMPOSITION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, CompositionFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + TableName.COMPOSITION_FEEDBACK.getValue() +
                "(ID, FEEDBACK, DATE) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + TableName.COMPOSITION_FEEDBACK.getValue() + " SET " +
                "ID=?, FEEDBACK=?, " +
                "DATE=? WHERE ID=?";
    }
}
