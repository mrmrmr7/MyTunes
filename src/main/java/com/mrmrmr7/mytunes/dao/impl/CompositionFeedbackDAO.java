package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionFeedbackDAO extends AbstractJDBCDAO<CompositionFeedback, Integer> implements GenericDAO<CompositionFeedback, Integer> {
    private final ResultSetCompiller resultSetCompiller = new ResultSetCompiller();
    private final ConnectionPool dbConnect = ConnectionPoolFactory
            .getInstance()
            .getConnectionPool(ConnectionPoolType.JDBC);

    public CompositionFeedbackDAO() {
    }

    @Override
    public Optional<CompositionFeedback> getByPK(Integer id) throws DAOException, SQLException {

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = prepareStatementForGet(connection, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return Optional.of(resultSetCompiller.setCompositionFeedback(resultSet));
        } catch (InterruptedException e) {
            throw new DAOException("Impossible to get connection");
        }
    }

    @Override
    public List<CompositionFeedback> getAll() throws SQLException {

        List<CompositionFeedback> compositionFeedbackList = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection()) {
            ResultSet resultSet = prepareStatementForGetAll(connection, TableName.COMPOSITION_FEEDBACK).executeQuery();
            while (resultSet.next()) {
                compositionFeedbackList
                        .add(resultSetCompiller.setCompositionFeedback(resultSet));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return compositionFeedbackList;
    }

    @Override
    public void insert(CompositionFeedback object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForInsert(connection, object)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForDelete(connection, id)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(CompositionFeedback object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForUpdate(connection, object)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Connection connection, CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Connection connection, CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.COMPOSITION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Connection connection, Integer id) throws SQLException {

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
