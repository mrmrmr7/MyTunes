package com.mrmrmr7.mytunes.service;

public class ServiceException extends Exception {
    public ServiceException(String false_user_id) {
        super(false_user_id);
    }
}
