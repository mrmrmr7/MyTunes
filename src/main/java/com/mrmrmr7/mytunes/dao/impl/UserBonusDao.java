package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.UserBonus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBonusDao extends AbstractJdbcDao<UserBonus, Integer> implements GenericDao<UserBonus, Integer> {

    public UserBonusDao() {
    }

    @Override
    public Optional<UserBonus> getByPK(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setUserBonus(resultSet));
            } catch (SQLException e) {
                throw new DAOException("4.13.1");
            }
        } catch (SQLException e) {
            throw new DAOException("4.13.2");
        }
    }

    @Override
    public List<UserBonus> getAll() throws DAOException {

        List<UserBonus> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.USER_BONUS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setUserBonus(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("4.13.3");
            }
        } catch (SQLException e) {
            throw new DAOException("4.13.4");
        }

        return userList;
    }

    @Override
    public void insert(UserBonus object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("4.13.5");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.13.6");
        }
    }

    @Override
    public void update(UserBonus object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("4.13.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(UserBonus object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        int j;
        for (int i = 0; i < object.getBonusIdList().size(); i++) {
            j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getBonusId(i));
            preparedStatement.addBatch();
        }
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(UserBonus object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection.prepareStatement(getUpdateQuery()), object);

        int j = 0;

        for (int i = 0; i < object.getBonusIdList().size(); i++) {
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getBonusId(i));
            preparedStatement.setInt(++j, object.getCortageId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.USER_BONUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.USER_BONUS), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, UserBonus object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.USER_BONUS.getValue() +
                "(USER_ID, BONUS_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.USER_BONUS.getValue() + " SET " +
                "USER_ID=?, BONUS_ID=? " +
                "WHERE ID=?";
    }

    @Override
    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE USER_ID=?";
    }
}
