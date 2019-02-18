package com.mrmrmr7.mytunes.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementCompiler {

    public PreparedStatement select(Connection connection, String what, String fromTable, String where) throws SQLException {
        String selectStatement = "SELECT " + what + " FROM " + fromTable + " WHERE " + where;
        return connection.prepareStatement(selectStatement);
    }

    public PreparedStatement select(Connection connection, String what, String fromTable) throws SQLException {
        return connection.prepareStatement("SELECT " + what + " FROM " + fromTable);
    }
}
