package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Bonus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BonusDao extends AbstractJdbcDao<Bonus, Integer> implements GenericDao<Bonus, Integer> {

    public BonusDao() {
    }

    @AutoConnection
    @Override
    public Optional<Bonus> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toBonus(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.4.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.4.2");
        }
    }

    @AutoConnection
    @Override
    public List<Bonus> getAll() throws DaoException {

        List<Bonus> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.BONUS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toBonus(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.4.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.4.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Bonus object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.4.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.4.6");
        }
    }

    @AutoConnection
    @Override
    public void update(Bonus object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.4.6");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Bonus object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Bonus object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.BONUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.BONUS));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Bonus object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getBonus());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.BONUS.getValue() +
                "(BONUS) " +
                "VALUES " +
                "(?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.BONUS.getValue() + " SET " +
                "BONUS=? " +
                "WHERE ID=?";
    }
}
