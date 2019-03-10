package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelectionFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionFeedbackDao extends AbstractJdbcDao<MusicSelectionFeedback, Integer> {

    public MusicSelectionFeedbackDao() {
    }

    @Override
    public Optional<MusicSelectionFeedback> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setMusicSelectionFeedback(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.9.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.9.2");
        }
    }

    @Override
    public List<MusicSelectionFeedback> getAll() throws DaoException {

        List<MusicSelectionFeedback> musicSelectionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.MUSIC_SELECTION_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    musicSelectionFeedbackList
                            .add(resultSetCompiller.setMusicSelectionFeedback(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.9.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.9.4");
        }

        return musicSelectionFeedbackList;
    }

    @Override
    public void insert(MusicSelectionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.9.5");
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.9.6");
        }
    }

    @Override
    public void update(MusicSelectionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.9.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(MusicSelectionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(MusicSelectionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.MUSIC_SELECTION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.MUSIC_SELECTION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, MusicSelectionFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + TableName.MUSIC_SELECTION_FEEDBACK.getValue() +
                "(ID, FEEDBACK, DATE) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + TableName.MUSIC_SELECTION_FEEDBACK.getValue() + " SET " +
                "ID=?, FEEDBACK=?, " +
                "DATE=? WHERE ID=?";
    }
}
