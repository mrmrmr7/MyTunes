package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetCompiller {
    public List<User> setUser(ResultSet resultSet) throws SQLException {
        int i = 1;
        List<User> userList = new ArrayList<>();

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
        return userList;
    }
    //лол
}
