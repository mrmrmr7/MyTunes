package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.service.DBConnectionService;
import com.mrmrmr7.mytunes.service.ResultSetCompiller;
import com.mrmrmr7.mytunes.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJDBCDAO<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;
    protected final ResultSetCompiller resultSetCompiller = new ResultSetCompiller();

    protected abstract PreparedStatement prepareStatementForInsert(T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForDelete(PK id) throws SQLException;

    protected abstract PreparedStatement prepareStatementForUpdate(T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForGet(PK id) throws SQLException;

    protected PreparedStatement prepareStatementForGetAll(TableName tableName) throws SQLException {
        return connection.prepareStatement(getSelectAllQuery(tableName));
    }

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();

    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE ID=?";
    }

    protected String getSelectAllQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue();
    }

    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE ID=?";
    }

    public void init() {
        try {
            connection = DBConnectionService.getConnection();
            System.out.println("DAO init successfully");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            DBConnectionService.closeConnection(connection);;
            System.out.println("DAO destroy successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
