package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.UserMusicSelectionDaoExtended;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserMusicSelection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMusicSelectionDao extends AbstractJdbcDao<UserMusicSelection, Integer> implements UserMusicSelectionDaoExtended {

    public UserMusicSelectionDao() {
    }

    @AutoConnection
    @Override
    public Optional<UserMusicSelection> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(resultSetToBean.toUserMusicSelection(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1144));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1145));
        }
    }

    @AutoConnection
    @Override
    public List<UserMusicSelection> getAll() throws DaoException {

        List<UserMusicSelection> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.USER_MUSIC_SELECTION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toUserMusicSelection(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1146));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1147));
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(UserMusicSelection object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1148));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1149));
        }
    }

    @AutoConnection
    @Override
    public void update(UserMusicSelection object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1150));
        }
    }

    @AutoConnection
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

    @AutoConnection
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

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.USER_MUSIC_SELECTION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.USER_MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getSelectQuery(Table table) {

        return "SELECT * FROM " + table.getValue() + " WHERE USER_ID=?";
    }

    @AutoConnection
    @Override
    protected String getSelectAllQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " ORDER BY USER_ID";
    }

    @AutoConnection
    @Override
    protected String getDeleteQuery(Table table) {
        return "DELETE FROM " + table.getValue() + " WHERE USER_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.USER_MUSIC_SELECTION.getValue() +
                "(USER_ID, MUSIC_SELECTION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.USER_MUSIC_SELECTION.getValue() + " SET " +
                "USER_ID=?, MUSIC_SELECTION_ID=? " +
                "WHERE ID=?";

    }

    @AutoConnection
    private PreparedStatement prepareStatementForGetByCompositionId(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByCompositionIdQuery(Table.USER_MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    public List<Integer> getCortageIdByMusicSelectionId(Integer id) throws DaoException {


        List<Integer> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetByCompositionId(id)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1151));
            }
        } catch (SQLException e) {
            throw new DaoException("4.14.4");
        }

        return userList;
    }

    @Override
    public Optional<UserMusicSelection> getByCortagePK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGetByCortageId(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(resultSetToBean.toUserMusicSelection(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1152));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1153));
        }

    }

    private PreparedStatement prepareStatementForGetByCortageId(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectCortageQuery(Table.USER_MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);

        return preparedStatement;

    }

    private String getSelectCortageQuery(Table userMusicSelection) {
        return "SELECT * FROM " + userMusicSelection.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    private String getSelectByCompositionIdQuery(Table musicSelection) {
        return "SELECT * FROM " + musicSelection.getValue() + " WHERE MUSIC_SELECTION_ID=?";
    }
}
