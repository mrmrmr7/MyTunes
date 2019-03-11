package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionDao extends AbstractJdbcDao<MusicSelection, Integer> implements GenericDao<MusicSelection, Integer> {

    public MusicSelectionDao() {
    }

    @AutoConnection
    @Override
    public Optional<MusicSelection> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toMusicSelection(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.8.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.8.2");
        }
    }

    @AutoConnection
    @Override
    public List<MusicSelection> getAll() throws DaoException {

        List<MusicSelection> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.MUSIC_SELECTION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toMusicSelection(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.8.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.8.4");
        }

        return userList;
    }

    @Override
    @AutoConnection
    public void insert(MusicSelection object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.8.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.8.6");
        }
    }

    @AutoConnection
    @Override
    public void update(MusicSelection object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.8.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(MusicSelection object) throws SQLException {

        List<Integer> compositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());

        for (int i = 0; i < compositionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getCompositionId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(MusicSelection object) throws SQLException {

        List<Integer> compositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

        for (int i = 0; i < compositionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getCompositionId(i));
            preparedStatement.setInt(++j, object.getCortageId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.MUSIC_SELECTION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getSelectQuery(TableName tableName) {

        return "SELECT * FROM " + tableName.getValue() + " WHERE SELECTION_ID=?";
    }

    @AutoConnection
    @Override
    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE SELECTION_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.MUSIC_SELECTION.getValue() +
                "(SELECTION_ID, COMPOSITION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.MUSIC_SELECTION.getValue() + " SET " +
                "SELECTION_ID=?, COMPOSITION_ID=? " +
                "WHERE ID=?";

    }
}
