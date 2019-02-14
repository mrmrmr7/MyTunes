package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBFill {
    public static synchronized void createDB() throws IOException, SQLException, InterruptedException {

        Connection connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.JDBC).getConnection();

        String dataBase = FileUtils
                .fileRead("src/main/resources/hsqldb/script/dbScheme.sql");

        connection
                .createStatement()
                .executeUpdate(dataBase);

        connection.close();
    }

    public static synchronized void fill() throws IOException, SQLException, InterruptedException {

        Connection connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.JDBC).getConnection();

        String fullTestData = FileUtils
                .fileRead("src/main/resources/hsqldb/script/fullTestData.sql");

        connection
                .createStatement()
                .executeUpdate(fullTestData);

        connection.close();
    }

    public static synchronized void drop() throws IOException, SQLException, InterruptedException {

        Connection connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.JDBC).getConnection();

        String fullTestData = FileUtils
                .fileRead("src/main/resources/hsqldb/script/dropDB.sql");

        connection
                .createStatement()
                .executeUpdate(fullTestData);

        connection.close();
    }
}
