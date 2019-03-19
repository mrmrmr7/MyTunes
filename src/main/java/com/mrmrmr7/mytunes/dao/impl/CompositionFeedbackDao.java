package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.util.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionFeedbackDao extends AbstractJdbcDao<CompositionFeedback, Integer> implements GenericDao<CompositionFeedback, Integer> {

    public CompositionFeedbackDao() {
    }

    @AutoConnection
    @Override
    public Optional<CompositionFeedback> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                };
                return Optional.of(resultSetToBean.toCompositionFeedback(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.6.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.6.2");
        }
    }

    @AutoConnection
    @Override
    public List<CompositionFeedback> getAll() throws DaoException {

        List<CompositionFeedback> compositionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.COMPOSITION_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    compositionFeedbackList
                            .add(resultSetToBean.toCompositionFeedback(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.6.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.6.4");
        }

        return compositionFeedbackList;
    }

    @AutoConnection
    @Override
    public void insert(CompositionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.6.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.6.6");
        }
    }

    @AutoConnection
    @Override
    public void update(CompositionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.6.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(CompositionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.COMPOSITION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.COMPOSITION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, CompositionFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    protected String getInsertQuery() {

        return "INSERT INTO " + Table.COMPOSITION_FEEDBACK.getValue() +
                "(ID, FEEDBACK, TIMESTAMP) " +
                "VALUES " +
                "(?,?,?)";
    }

    @AutoConnection
    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + Table.COMPOSITION_FEEDBACK.getValue() + " SET " +
                "ID=?, FEEDBACK=?, " +
                "TIMESTAMP=? WHERE ID=?";
    }
}
