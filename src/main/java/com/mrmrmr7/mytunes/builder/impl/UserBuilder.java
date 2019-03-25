package com.mrmrmr7.mytunes.builder.impl;

import com.mrmrmr7.mytunes.builder.Builder;
import com.mrmrmr7.mytunes.builder.exception.BuilderException;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public class UserBuilder implements Builder<User> {

    public User build(HttpServletRequest request) throws BuilderException {
        String login = request.getParameter(RequestDirector.LOGIN.getValue());
        String email = request.getParameter(RequestDirector.EMAIL.getValue());
        String firstName = request.getParameter(RequestDirector.FIRST_NAME.getValue());
        String secondName = request.getParameter(RequestDirector.SECOND_NAME.getValue());
        String password = request.getParameter(RequestDirector.PASSWORD.getValue());

        if (login == null || email == null || firstName == null || secondName == null || password == null) {
            throw new BuilderException();
        }

        if (login.isEmpty() || email.isEmpty() || firstName.isEmpty() || secondName.isEmpty() || password.isEmpty()) {
            throw new BuilderException();
        }

        String passwordBCrypted = BCrypt.hashpw(password, BCrypt.gensalt());

        return new User(login, passwordBCrypted, firstName, secondName, email);
    }
}
