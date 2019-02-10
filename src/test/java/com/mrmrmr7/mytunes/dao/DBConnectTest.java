package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBConnectTest {

    @Test
    void getConnection() throws Exception {
        ConnectionPool dbConnect = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.JDBC);

        DBFill.fill();

        try (Connection connection = dbConnect.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
            preparedStatement.clearParameters();
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> userList = new ArrayList<>();
            int i;
            while (resultSet.next()) {
                i = 1;
                userList.add(
                        new User(resultSet.getInt(i++),
                                resultSet.getDate(i++),
                                resultSet.getString(i++),
                                resultSet.getString(i++),
                                resultSet.getString(i++),
                                resultSet.getString(i++),
                                resultSet.getString(i++),
                                resultSet.getLong(i++),
                                resultSet.getByte(i++),
                                resultSet.getByte(i++),
                                resultSet.getByte(i))
                );
            }

            System.out.println(userList.toString());

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