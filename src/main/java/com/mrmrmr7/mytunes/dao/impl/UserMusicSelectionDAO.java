package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.UserMusicSelection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMusicSelectionDAO extends AbstractJDBCDAO<UserMusicSelection, Integer> {

    public UserMusicSelectionDAO() {
    }

    @Override
    public Optional<UserMusicSelection> getByPK(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setUserMusicSelection(resultSet));
            } catch (SQLException e) {
                throw new DAOException("4.16.1");
            }
        } catch (SQLException e) {
            throw new DAOException("4.16.2");
        }
    }

    @Override
    public List<UserMusicSelection> getAll() throws DAOException {

        List<UserMusicSelection> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.USER_MUSIC_SELECTION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setUserMusicSelection(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("4.16.3");
            }
        } catch (SQLException e) {
            throw new DAOException("4.16.4");
        }

        return userList;
    }

    @Override
    public void insert(UserMusicSelection object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("4.16.5");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.16.6");
        }
    }

    @Override
    public void update(UserMusicSelection object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("4.16.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(UserMusicSelection object) throws SQLException {

        List<Integer> userSelectionIdList = object.getMusicSelectionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());

        for (int i = 0; i < userSelectionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId().intValue());
            preparedStatement.setInt(++j, object.getSelectionId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(UserMusicSelection object) throws SQLException {

        List<Integer> userSelectionIdList = object.getMusicSelectionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

        for (int i = 0; i < userSelectionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getSelectionId(i));
            preparedStatement.setInt(++j, object.getCortageId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER_MUSIC_SELECTION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.USER_MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected String getSelectQuery(TableName tableName) {

        return "SELECT * FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }

    @Override
    protected String getSelectAllQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " ORDER BY USER_ID";
    }

    @Override
    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.USER_MUSIC_SELECTION.getValue() +
                "(USER_ID, SELECTION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.USER_MUSIC_SELECTION.getValue() + " SET " +
                "USER_ID=?, SELECTION_ID=? " +
                "WHERE ID=?";

    }
}
