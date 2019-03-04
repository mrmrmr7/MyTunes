package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.SessionData;

public interface ServiceSession {
    String execute(String login) throws ServiceException;
    boolean check(SessionData sessionData) throws ServiceException;
}
