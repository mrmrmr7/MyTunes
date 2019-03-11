package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.util.TableName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {
    protected Connection connection;

    protected ResultSetToBean resultSetToBean = new ResultSetToBean();

    public AbstractJdbcDao() { }

    @AutoConnection
    protected abstract PreparedStatement prepareStatementForInsert(T object) throws SQLException;

    @AutoConnection
    protected abstract PreparedStatement prepareStatementForDelete(PK id) throws SQLException;

    @AutoConnection
    protected abstract PreparedStatement prepareStatementForUpdate(T object) throws SQLException;

    @AutoConnection
    protected abstract PreparedStatement prepareStatementForGet(PK id) throws SQLException;

    @AutoConnection
    protected PreparedStatement prepareStatementForGetAll(TableName tableName) throws SQLException {
        return connection.prepareStatement(getSelectAllQuery(tableName), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @AutoConnection
    protected abstract String getInsertQuery();

    @AutoConnection
    protected abstract String getUpdateQuery();

    @AutoConnection
    protected String getSelectQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    protected String getSelectAllQuery(TableName tableName) {
        return "SELECT * FROM " + tableName.getValue();
    }

    @AutoConnection
    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    public Connection getConnection() {
        return connection;
    }

    @AutoConnection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @AutoConnection
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {

        }
        this.connection = null;
    }
}
