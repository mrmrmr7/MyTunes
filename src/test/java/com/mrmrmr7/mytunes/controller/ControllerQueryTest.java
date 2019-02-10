package com.mrmrmr7.mytunes.controller;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

class ControllerQueryTest {

    @Test
    void getById() throws DAOException, IOException, SQLException, InterruptedException {
        DBFill.fill();

        ControllerQuery controllerQuery = ControllerQuery.getInstance();

        try {
            List<User> userList = controllerQuery.getById(1);
            System.out.println(userList.toString());
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}