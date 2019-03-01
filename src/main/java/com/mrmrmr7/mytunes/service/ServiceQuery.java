package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.impl.UserDAO;

import java.sql.SQLException;

public class ServiceQuery {
    private static ServiceQuery ourInstance = new ServiceQuery();

    public static ServiceQuery getInstance() {
        return ourInstance;
    }

    private ServiceQuery() {
    }

    public void  doSmt() {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.getAll();
        } catch (SQLException e) {

        }
    }
}
