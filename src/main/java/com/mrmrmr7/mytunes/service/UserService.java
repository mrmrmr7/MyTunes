package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {

    boolean isRightUser(String login, String password) throws ServiceException;

    Map<String, Cookie> getCookies(String login) throws ServiceException;

    void logout(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException;

    boolean tryRestartPassword(String email) throws ServiceException;

    boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException;

    void changePassword(int id, String password) throws ServiceException;
}
