package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionDAO extends AbstractJDBCDAO<Composition, Integer> implements GenericDAO<Composition, Integer> {
    private final static CompositionDAO ourInstance = new CompositionDAO();
    private final ResultSetCompiller resultSetCompiller = new ResultSetCompiller();
    private final ConnectionPool dbConnect = ConnectionPoolFactory
            .getInstance()
            .getConnectionPool(ConnectionPoolType.JDBC);

    public static CompositionDAO getInstance() {
        return ourInstance;
    }

    public CompositionDAO() {
    }

    @Override
    public Optional<Composition> getByPK(Integer id) throws DAOException, SQLException {

        try (Connection connection = dbConnect.getConnection()) {
            ResultSet resultSet = prepareStatementForGet(connection, id)
                    .executeQuery();
            resultSet.next();
            return Optional.of(resultSetCompiller.setComposition(resultSet));
        } catch (InterruptedException e) {
            throw new DAOException("Impossible to get connection");
        }
    }

    @Override
    public List<Composition> getAll() throws SQLException {

        List<Composition> userList = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection()) {
            ResultSet resultSet = prepareStatementForGetAll(connection, TableName.COMPOSITION).executeQuery();
            while (resultSet.next()) {
                userList
                        .add(resultSetCompiller.setComposition(resultSet));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void insert(Composition object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForInsert(connection, object)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForDelete(connection, id)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Composition object) throws SQLException {

        try (Connection connection = dbConnect.getConnection()){
            prepareStatementForUpdate(connection, object)
                    .executeUpdate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Connection connection, Composition object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Connection connection, Composition object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.COMPOSITION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Connection connection, Integer id) throws SQLException {

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
                "ALBUM_ID=?" +
                "WHERE ID=?";

    }
}
