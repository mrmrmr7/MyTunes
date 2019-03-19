package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumFeedbackDao extends AbstractJdbcDao<AlbumFeedback, Integer> implements GenericDao<AlbumFeedback, Integer> {

    public AlbumFeedbackDao() {
    }

    @AutoConnection
    @Override
    public Optional<AlbumFeedback> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        return Optional.empty();
                    }
                    return Optional.of(resultSetToBean.toAlbumFeedback(resultSet));
                } catch (SQLException e) {
                    throw new DaoException("4.2.0");
                }
        } catch (SQLException e) {
            throw new DaoException("4.2.1");
        }
    }

    @AutoConnection
    @Override
    public List<AlbumFeedback> getAll() throws DaoException {

        List<AlbumFeedback> compositionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.ALBUM_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    compositionFeedbackList
                            .add(resultSetToBean.toAlbumFeedback(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.2.2");
            }
        } catch (SQLException e) {
            throw new DaoException("4.2.3");
        }

        return compositionFeedbackList;
    }

    @AutoConnection
    @Override
    public void insert(AlbumFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.2.3");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.2.4");
        }
    }

    @AutoConnection
    @Override
    public void update(AlbumFeedback object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.2.5");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(AlbumFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        preparedStatement.setInt(3, object.getId());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(AlbumFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setString(1, object.getFeedback());
        preparedStatement.setTimestamp(2, object.getTimestamp());
        preparedStatement.setInt(3, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.ALBUM_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.ALBUM_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, AlbumFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setTimestamp(++i, object.getTimestamp());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    protected String getInsertQuery() {

        return "INSERT INTO " + Table.ALBUM_FEEDBACK.getValue() +
                "(FEEDBACK, TIMESTAMP, ID) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    @AutoConnection
    protected String getUpdateQuery() {

        return "UPDATE " + Table.ALBUM_FEEDBACK.getValue() + " SET " +
                " FEEDBACK=?, " +
                "TIMESTAMP=? WHERE ID=?";
    }
}
