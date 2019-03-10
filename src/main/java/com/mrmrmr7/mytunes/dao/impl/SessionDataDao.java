package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.SessionData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDataDao extends AbstractJdbcDao<SessionData, Integer> implements GenericDao<SessionData, Integer> {

    public SessionDataDao() {
    }

    @Override
    public Optional<SessionData> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setSessionData(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.10.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.10.2");
        }
    }

    @Override
    public List<SessionData> getAll() throws DaoException {

        List<SessionData> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.SESSION_DATA)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setSessionData(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.10.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.10.4");
        }

        return userList;
    }

    @Override
    public void insert(SessionData object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.5");
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.6");
        }
    }

    @Override
    public void update(SessionData object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.10.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(SessionData object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        preparedStatement.setInt(1, object.getUser_id());
        preparedStatement.setBytes(2, object.getSession_hash());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(SessionData object) throws SQLException {

        PreparedStatement preparedStatement = connection
                        .prepareStatement(getUpdateQuery());
        preparedStatement.setInt(2,object.getUser_id());
        preparedStatement.setBytes(1, object.getSession_hash());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.SESSION_DATA));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.SESSION_DATA));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.SESSION_DATA.getValue() +
                "(USER_ID, SESSION_HASH) " +
                "VALUES " +
                "(?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.SESSION_DATA.getValue() + " SET " +
                "SESSION_HASH=? " +
                "WHERE USER_ID=?";
    }

    @Override
    public String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }
}
