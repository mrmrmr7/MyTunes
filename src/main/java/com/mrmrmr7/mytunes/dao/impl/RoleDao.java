package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.TableName;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao extends AbstractJdbcDao<Role, Integer> implements GenericDao<Role, Integer> {

    public RoleDao() {
    }

    @Override
    public Optional<Role> getByPK(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetCompiller.setRole(resultSet));
            } catch (SQLException e) {
                throw new DAOException("4.10.1");
            }
        } catch (SQLException e) {
            throw new DAOException("4.10.2");
        }
    }

    @Override
    public List<Role> getAll() throws DAOException {

        List<Role> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.ROLE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setRole(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("4.10.3");
            }
        } catch (SQLException e) {
            throw new DAOException("4.10.4");
        }

        return userList;
    }

    @Override
    public void insert(Role object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.10.5");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.10.6");
        }
    }

    @Override
    public void update(Role object) throws DAOException {

        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("4.10.7");
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(Role object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(Role object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(connection
                        .prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(2, object.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.ROLE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.ROLE));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, Role object) throws SQLException {

        int i = 0;
        preparedStatement.setString(++i, object.getRole());
        return preparedStatement;
    }

    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.ROLE.getValue() +
                "(ROLE) " +
                "VALUES " +
                "(?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.ROLE.getValue() + " SET " +
                "ROLE=? " +
                "WHERE ID=?";
    }
}
