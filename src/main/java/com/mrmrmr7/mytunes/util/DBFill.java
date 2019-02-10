package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBFill {
    public static void fill() throws DAOException, IOException, SQLException, InterruptedException {
        Connection connection = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.JDBC)
                .getConnection();

        String dataBase = FileUtils
                .fileRead("src/main/resources/hsqldb/script/dbScheme.sql");

        connection
                .createStatement()
                .executeUpdate(dataBase);

        String fullTestData = FileUtils
                .fileRead("src/main/resources/hsqldb/script/fullTestData.sql");

        connection
                .createStatement()
                .executeUpdate(fullTestData);
    }
}
