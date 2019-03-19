package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.util.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionDao extends AbstractJdbcDao<Composition, Integer> implements CompositionDaoExtended {

    public CompositionDao() {
    }

    @AutoConnection
    @Override
    public Optional<Composition> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toComposition(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.5.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.5.2");
        }
    }

    @AutoConnection
    @Override
    public List<Composition> getListByPK(List<Integer> idList) throws DaoException {

        List<Composition> compositionList = new ArrayList<>();

        for (Integer s : idList) {
            try {
                getByPK(s).ifPresent(compositionList::add);
            } catch (DaoException e) {
                throw new DaoException(e.getMessage());
            }
        }

        return compositionList;
    }

    @Override
    public Optional<Composition> getByName(String name) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGetByName(name)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toComposition(resultSet));
            } catch (SQLException e) {
                throw new DaoException("4.5.1");
            }
        } catch (SQLException e) {
            throw new DaoException("4.5.2");
        }
    }

    @AutoConnection
    @Override
    public List<Composition> getAll() throws DaoException {

        List<Composition> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.COMPOSITION)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toComposition(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException("4.5.3");
            }
        } catch (SQLException e) {
            throw new DaoException("4.5.4");
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(Composition object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.5.5");
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.5.6");
        }
    }

    @AutoConnection
    @Override
    public void update(Composition object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("4.5.7");
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(Composition object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(Composition object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    protected PreparedStatement prepareStatementForGetByName(String name) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByNameQuery(Table.COMPOSITION));
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @AutoConnection
    protected String getSelectByNameQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE NAME=?";
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Composition object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getPrice());
        preparedStatement.setString(++i, object.getName());
        preparedStatement.setInt(++i, object.getAlbum_id());
        preparedStatement.setInt(++i, object.getYear());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.COMPOSITION.getValue() +
                "(PRICE, NAME, ALBUM_ID, YEAR) " +
                "VALUES " +
                "(?,?,?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.COMPOSITION.getValue() + " SET " +
                "PRICE=?, NAME=?, " +
                "ALBUM_ID=? " +
                "WHERE ID=?";
    }
}
