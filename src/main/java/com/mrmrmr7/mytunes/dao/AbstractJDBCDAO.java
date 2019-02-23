package com.mrmrmr7.mytunes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJDBCDAO<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;

    protected abstract PreparedStatement prepareStatementForInsert(Connection connection, T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForDelete(Connection connection, PK id) throws SQLException;

    protected abstract PreparedStatement prepareStatementForUpdate(Connection connection, T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForGet(Connection connection, PK id) throws SQLException;

    protected PreparedStatement prepareStatementForGetAll(Connection connection, TableName tableName) throws SQLException {
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

}
