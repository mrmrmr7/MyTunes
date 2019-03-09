package com.mrmrmr7.mytunes.validator;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationValidator {
    public boolean isValid(String command, HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            return false;
        }

        if (httpServletRequest.getSession(false) == null) {
            return false;
        }

        if (command == null) {
            return false;
        }

        return true;
    }
}
