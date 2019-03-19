package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServiceUser {

    boolean login(String login, String password, HttpServletResponse response) throws ServiceException;

    void logout(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException;

    boolean register(User user);

    boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException;
}
