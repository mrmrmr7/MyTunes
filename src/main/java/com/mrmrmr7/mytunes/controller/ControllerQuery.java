package com.mrmrmr7.mytunes.controller;

import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceQuery;
import com.mrmrmr7.mytunes.entity.User;

import java.util.List;

public class ControllerQuery {
    private static ControllerQuery ourInstance = new ControllerQuery();

    public static ControllerQuery getInstance() {
        return ourInstance;
    }

    private ControllerQuery() {
    }

    public List<User> getById(int id) throws ControllerException {
        if (id > 0) {
            ServiceQuery serviceQuery = ServiceQuery.getInstance();
            try {
                List<User> userList = serviceQuery.getByID(id);
                return userList;
            } catch (ServiceException e) {
                throw new ControllerException("Can't get user by negative id");
            }
        } else {
            throw new ControllerException("Negative id");
        }
    }
}
