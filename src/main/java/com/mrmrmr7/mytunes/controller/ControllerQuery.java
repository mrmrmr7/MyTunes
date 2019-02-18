package com.mrmrmr7.mytunes.controller;

public class ControllerQuery {
    private static ControllerQuery ourInstance = new ControllerQuery();

    public static ControllerQuery getInstance() {
        return ourInstance;
    }

    private ControllerQuery() {
    }
}
