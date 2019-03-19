package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
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

    @AutoConnection
    @Override
    public Optional<UserBonus> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toUserBonus(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.13.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.13.2");
        }
    }

    @AutoConnection
    @Override
    public List<UserBonus> getAll() throws DaoException {

        List<UserBonus> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.USER_BONUS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toUserBonus(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.13.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.13.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(UserBonus object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.13.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.13.6");
        }
    }

    @AutoConnection
    @Override
    public void update(UserBonus object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("4.13.7");
        }
    }

    @AutoConnection
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

    @AutoConnection
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

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.USER_BONUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.USER_BONUS), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, UserBonus object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.USER_BONUS.getValue() +
                "(USER_ID, BONUS_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.USER_BONUS.getValue() + " SET " +
                "USER_ID=?, BONUS_ID=? " +
                "WHERE ID=?";
    }

    @AutoConnection
    @Override
    protected String getSelectQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE USER_ID=?";
    }
}
