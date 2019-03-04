package com.mrmrmr7.mytunes.service;

public interface ServiceSignIn {
    boolean execute(String login, String password) throws ServiceException;
}
