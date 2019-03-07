package com.mrmrmr7.mytunes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractJDBCDAO<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;

    protected ResultSetCompiller resultSetCompiller = new ResultSetCompiller();

    public AbstractJDBCDAO() { }

    protected abstract PreparedStatement prepareStatementForInsert(T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForDelete(PK id) throws SQLException;

    protected abstract PreparedStatement prepareStatementForUpdate(T object) throws SQLException;

    protected abstract PreparedStatement prepareStatementForGet(PK id) throws SQLException;

    protected PreparedStatement prepareStatementForGetAll(TableName tableName) throws SQLException {
        return connection.prepareStatement(getSelectAllQuery(tableName), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {

        }
        this.connection = null;
    }
}
