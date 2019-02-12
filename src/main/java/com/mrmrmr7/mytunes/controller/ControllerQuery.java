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
}
