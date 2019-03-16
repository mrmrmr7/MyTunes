package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.UserAlbumDaoExtended;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserAlbum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAlbumDao extends AbstractJdbcDao<UserAlbum, Integer> implements UserAlbumDaoExtended {

    public UserAlbumDao() {
    }

    @AutoConnection
    @Override
    public Optional<UserAlbum> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toUserAlbum(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.12.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.12.2");
        }
    }

    @AutoConnection
    @Override
    public List<UserAlbum> getAll() throws DaoException {

        List<UserAlbum> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.USER_ALBUM)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toUserAlbum(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.12.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.12.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(UserAlbum object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.12.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.12.6");
        }
    }

    @AutoConnection
    @Override
    public void update(UserAlbum object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.12.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(UserAlbum object) throws SQLException {

        List<Integer> userAlbumIdList = object.getAlbumIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());

        for (int i = 0; i < userAlbumIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getAlbumId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(UserAlbum object) throws SQLException {

        List<Integer> userAlbumIdList = object.getAlbumIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

        for (int i = 0; i < userAlbumIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getAlbumId(i));
            preparedStatement.setInt(++j, object.getCortageId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER_ALBUM));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.USER_ALBUM), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getSelectQuery(TableName tableName) {

        return "SELECT * FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }

    @AutoConnection
    @Override
    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.USER_ALBUM.getValue() +
                "(USER_ID, ALBUM_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.USER_ALBUM.getValue() + " SET " +
                "USER_ID=?, ALBUM_ID=? " +
                "WHERE ID=?";

    }

    @AutoConnection
    @Override
    public List<Integer> getCortageIdByAlbumId(Integer id) throws DaoException {

        List<Integer> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetByCompositionId(id)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                throw new DaoException("4.14.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.14.4");
        }

        return userList;
    }

    @AutoConnection
    private PreparedStatement prepareStatementForGetByCompositionId(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByCompositionIdQuery(TableName.USER_ALBUM), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByCompositionIdQuery(TableName album) {
        return "SELECT * FROM " + album.getValue() + " WHERE album_ID=?";
    }
}
