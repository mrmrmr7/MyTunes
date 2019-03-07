package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ServiceUser {

    boolean login(String login, String password, HttpServletResponse response) throws ServiceException;

    void logout(HttpSession session) throws ServiceException;

    boolean register(User user);

    boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException;
}
