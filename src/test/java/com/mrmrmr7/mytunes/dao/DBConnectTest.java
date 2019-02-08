package com.mrmrmr7.mytunes.dao;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBConnectTest {

    @Test
    void getConnection() throws Exception {
        DBConnect dbConnect = new DBConnect(
                "jdbc:hsqldb:file:src/main/resources/hsqldb/database/mytunes",
                "SA",
                "",
                "org.hsqldb.jdbc.JDBCDriver"
        );

        try (Connection connection = dbConnect.getConnection()){
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



            String expected = "2.4.1";
            String actual = connection
                    .getMetaData()
                    .getDatabaseProductVersion();

            assertEquals(expected, actual);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}