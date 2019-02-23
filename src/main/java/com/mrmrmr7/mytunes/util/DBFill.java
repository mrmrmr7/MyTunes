package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.service.DBConnectionService;
import com.mrmrmr7.mytunes.service.ServiceException;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFill {
    public static synchronized void createDB() throws IOException, SQLException, InterruptedException {

        Connection connection = null;
        try {
            connection = DBConnectionService.getConnection();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String dataBase = FileUtils
                .fileRead("src/main/resources/hsqldb/script/dbScheme.sql");
        Statement statement = connection
                .createStatement();


        statement.executeUpdate(dataBase);

        statement.close();

        DBConnectionService.closeConnection(connection);
    }

    public static synchronized void fill() throws IOException, SQLException, InterruptedException {

        Connection connection = null;
        try {
            connection = DBConnectionService.getConnection();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String fullTestData = FileUtils
                .fileRead("src/main/resources/hsqldb/script/fullTestData.sql");
        Statement statement = connection
                .createStatement();
        statement.executeUpdate(fullTestData);

        statement.close();

        DBConnectionService.closeConnection(connection);
    }

    public static synchronized void drop() throws IOException, SQLException, InterruptedException {

        Connection connection = ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.JDBC).getConnection();

        String fullTestData = FileUtils
                .fileRead("src/main/resources/hsqldb/script/dropDB.sql");
        Statement statement = connection
                .createStatement();


        statement.executeUpdate(fullTestData);

        statement.close();

        DBConnectionService.closeConnection(connection);
    }
}
