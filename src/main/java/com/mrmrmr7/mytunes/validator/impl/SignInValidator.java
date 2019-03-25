package com.mrmrmr7.mytunes.validator.impl;

import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.validator.Validator;

import java.util.Map;

public class SignInValidator implements Validator {
    @Override
    public boolean validate(Map<String,String> map) {
        String login = map.get(RequestDirector.LOGIN.getValue());
        String password = map.get(RequestDirector.PASSWORD.getValue());

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
