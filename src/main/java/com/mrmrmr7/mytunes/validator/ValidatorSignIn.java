package com.mrmrmr7.mytunes.validator;

public class ValidatorSignIn {
    public boolean isValid(String login, String password) {
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
