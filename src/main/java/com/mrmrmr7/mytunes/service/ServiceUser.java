package com.mrmrmr7.mytunes.service;

public interface ServiceUser {
    int getIdByLogin(String login) throws ServiceException;
}
