package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelectionFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionFeedbackDao extends AbstractJdbcDao<MusicSelectionFeedback, Integer> {

    public MusicSelectionFeedbackDao() {
    }

    @AutoConnection
    @Override
    public Optional<MusicSelectionFeedback> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(resultSetToBean.toMusicSelectionFeedback(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1068));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1069));
        }
    }

    @AutoConnection
    @Override
    public List<MusicSelectionFeedback> getAll() throws DaoException {

        List<MusicSelectionFeedback> musicSelectionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.MUSIC_SELECTION_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    musicSelectionFeedbackList
                            .add(resultSetToBean.toMusicSelectionFeedback(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1070));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1071));
        }

        return musicSelectionFeedbackList;
    }

    @AutoConnection
    @Override
    public void insert(MusicSelectionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1072));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1073));
        }
    }

    @AutoConnection
    @Override
    public void update(MusicSelectionFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1074));
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(MusicSelectionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(MusicSelectionFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(3, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.MUSIC_SELECTION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.MUSIC_SELECTION_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, MusicSelectionFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + Table.MUSIC_SELECTION_FEEDBACK.getValue() +
                "(ID, FEEDBACK, TIMESTAMP) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + Table.MUSIC_SELECTION_FEEDBACK.getValue() + " SET " +
                "FEEDBACK=?, " +
                "TIMESTAMP=? WHERE ID=?";
    }
}
