package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.UserCompositionDaoExtended;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserComposition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCompositionDao extends AbstractJdbcDao<UserComposition, Integer> implements UserCompositionDaoExtended {

    public UserCompositionDao() {
    }

    @AutoConnection
    @Override
    public Optional<UserComposition> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(resultSetToBean.toUserComposition(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.14.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.14.2");
        }

    }

    @AutoConnection
    @Override
    public List<UserComposition> getAll() throws DaoException {

        List<UserComposition> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.USER_COMPOSITION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toUserComposition(resultSet));
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
    @Override
    public List<Integer> getCortageIdByCompositionId(Integer id) throws DaoException {

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
    @Override
    public Optional<UserComposition> getByCortagePK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGetByCortageId(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(resultSetToBean.toUserComposition(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.14.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.14.2");
        }

    }

    private PreparedStatement prepareStatementForGetByCortageId(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectCortageQuery(TableName.USER_COMPOSITION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }

    private String getSelectCortageQuery(TableName userComposition) {
        return "SELECT * FROM " + userComposition.getValue() + " WHERE ID=?";
    }

    private PreparedStatement prepareStatementForGetByCompositionId(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByCompositionIdQuery(TableName.USER_COMPOSITION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByCompositionIdQuery(TableName userComposition) {
        return "SELECT * FROM " + userComposition.getValue() + " WHERE COMPOSITION_ID=?";
    }

    @AutoConnection
    @Override
    public void insert(UserComposition object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.14.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.14.6");
        }
    }

    @AutoConnection
    @Override
    public void update(UserComposition object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.14.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(UserComposition object) throws SQLException {

        List<Integer> userCompositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());

        for (int i = 0; i < userCompositionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getCompositionId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(UserComposition object) throws SQLException {

        List<Integer> userCompositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

        for (int i = 0; i < userCompositionIdList.size(); i++) {
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

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER_COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.USER_COMPOSITION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

        return "INSERT INTO " + TableName.USER_COMPOSITION.getValue() +
                "(USER_ID, COMPOSITION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.USER_COMPOSITION.getValue() + " SET " +
                "USER_ID=?, COMPOSITION_ID=? " +
                "WHERE ID=?";

    }
}
