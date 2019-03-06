package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.GenericDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumFeedbackDAO extends AbstractJDBCDAO<AlbumFeedback, Integer> implements GenericDAO<AlbumFeedback, Integer> {

    public AlbumFeedbackDAO() {
    }

    @Override
    public Optional<AlbumFeedback> getByPK(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return Optional.of(resultSetCompiller.setAlbumFeedback(resultSet));
                } catch (SQLException e) {
                    throw new DAOException("4.2.0");
                }
        } catch (SQLException e) {
            throw new DAOException("4.2.1");
        }
    }

    @Override
    public List<AlbumFeedback> getAll() throws DAOException {

        List<AlbumFeedback> compositionFeedbackList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.ALBUM_FEEDBACK)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    compositionFeedbackList
                            .add(resultSetCompiller.setAlbumFeedback(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("4.2.2");
            }
        } catch (SQLException e) {
            throw new DAOException("4.2.3");
        }

        return compositionFeedbackList;
    }

    @Override
    public void insert(AlbumFeedback object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.2.3");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.2.4");
        }
    }

    @Override
    public void update(AlbumFeedback object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.2.5");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(AlbumFeedback object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        preparedStatement.setInt(3, object.getId());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(AlbumFeedback object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setString(1, object.getFeedback());
        preparedStatement.setDate(2, object.getDate());
        preparedStatement.setInt(3, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.ALBUM_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.ALBUM_FEEDBACK));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, AlbumFeedback object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getFeedback());
        preparedStatement.setDate(++i, object.getDate());
        return preparedStatement;
    }

    @Override
    protected String getInsertQuery() {

        return "INSERT INTO " + TableName.ALBUM_FEEDBACK.getValue() +
                "(FEEDBACK, DATE, ID) " +
                "VALUES " +
                "(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {

        return "UPDATE " + TableName.ALBUM_FEEDBACK.getValue() + " SET " +
                " FEEDBACK=?, " +
                "DATE=? WHERE ID=?";
    }
}
