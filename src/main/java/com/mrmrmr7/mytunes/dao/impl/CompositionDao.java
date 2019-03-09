package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.util.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionDao extends AbstractJdbcDao<Composition, Integer> implements CompositionDaoExtended {

    public CompositionDao() {
    }

    @Override
    public Optional<Composition> getByPK(Integer id) throws DAOException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setComposition(resultSet));
            } catch (SQLException e) {
                throw new DAOException("4.5.1");
            }
        } catch (SQLException e) {
            throw new DAOException("4.5.2");
        }
    }

    @Override
    public List<Composition> getListByPK(List<Integer> idList) throws DAOException {

        List<Composition> compositionList = new ArrayList<>();

        for (Integer s : idList) {
            try {
                getByPK(s).ifPresent(compositionList::add);
            } catch (DAOException e) {
                throw new DAOException(e.getMessage());
            }
        }

        return compositionList;
    }

    @Override
    public List<Composition> getAll() throws DAOException {

        List<Composition> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.COMPOSITION)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setComposition(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("4.5.3");
            }
        } catch (SQLException e) {
            throw new DAOException("4.5.4");
        }

        return userList;
    }

    @Override
    public void insert(Composition object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.5.5");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.5.6");
        }
    }

    @Override
    public void update(Composition object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.5.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Composition object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Composition object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Composition object) throws SQLException {

        int i = 0;
        preparedStatement.setInt(++i, object.getPrice());
        preparedStatement.setString(++i, object.getName());
        preparedStatement.setInt(++i, object.getAlbum_id());
        preparedStatement.setInt(++i, object.getYear());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.COMPOSITION.getValue() +
                "(PRICE, NAME, ALBUM_ID, YEAR) " +
                "VALUES " +
                "(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.COMPOSITION.getValue() + " SET " +
                "PRICE=?, NAME=?, " +
                "ALBUM_ID=? " +
                "WHERE ID=?";
    }
}
