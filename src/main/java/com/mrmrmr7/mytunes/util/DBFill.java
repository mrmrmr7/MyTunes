package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFill {
    public static synchronized void createDB() throws IOException, SQLException, InterruptedException {

        Connection connection = null;

        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String dataBase = FileUtils
                .fileRead("src/test/resources/hsqldb/script/dbScheme.sql");
        Statement statement = connection
                .createStatement();


        statement.executeUpdate(dataBase);

        statement.close();

        connection.close();
    }

    public static synchronized void fill() throws IOException, SQLException {

        Connection connection = null;

        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String fullTestData = FileUtils
                .fileRead("src/test/resources/hsqldb/script/fullTestData.sql");

        Statement statement = connection
                .createStatement();

        statement.executeUpdate(fullTestData);

        statement.close();

        connection.close();
    }

    public static synchronized void drop() throws IOException, SQLException, InterruptedException {

        Connection connection = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String fullTestData = FileUtils
                .fileRead("src/test/resources/hsqldb/script/dropDB.sql");

        Statement statement = connection
                .createStatement();


        statement.executeUpdate(fullTestData);

        statement.close();

        connection.close();
    }
}
