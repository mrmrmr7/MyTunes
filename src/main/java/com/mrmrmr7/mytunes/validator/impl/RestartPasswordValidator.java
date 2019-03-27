package com.mrmrmr7.mytunes.validator.impl;

import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.service.UserService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.validator.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class RestartPasswordValidator implements Validator {

    @Override
    public boolean validate(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String token = request.getParameter(RequestDirector.TOKEN.getValue());
        String publicKey = request.getParameter(RequestDirector.PUBLIC_KEY.getValue());

        UserService userService = new UserServiceImpl();
        if (!token.isEmpty() && !publicKey.isEmpty()){
            try {
                return ((UserServiceImpl) userService).validateTokenById(token, publicKey);
            } catch (ServiceException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
