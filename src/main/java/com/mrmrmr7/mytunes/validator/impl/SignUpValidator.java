package com.mrmrmr7.mytunes.validator.impl;

import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class SignUpValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest request) {
        String login = request.getParameter(RequestDirector.LOGIN.getValue());
        String password = request.getParameter(RequestDirector.PASSWORD.getValue());

        if (login == null || password == null) {
            return false;
        }

        boolean isNormalPass = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$");

        if (!isNormalPass) {
            return false;
        }

        boolean isNormalLogin = login.matches("^[0-9a-zA-Z_]{8,32}$");

        if (!isNormalLogin) {
            return false;
        }

        return true;
    }
}
