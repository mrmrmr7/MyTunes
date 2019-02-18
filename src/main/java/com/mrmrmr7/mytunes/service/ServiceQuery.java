package com.mrmrmr7.mytunes.service;

public class ServiceQuery {
    private static ServiceQuery ourInstance = new ServiceQuery();

    public static ServiceQuery getInstance() {
        return ourInstance;
    }

    private ServiceQuery() {
    }
}
