package com.mrmrmr7.mytunes.validator.impl;

import com.mrmrmr7.mytunes.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class FinishRestartPasswordValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest request) {
        String password = request.getParameter("password");
        return request.getParameter("userId") != null && password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$");
    }
}
