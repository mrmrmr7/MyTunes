package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.service.impl.ServiceUserImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandTrySignUp implements Command {
    private final static String PARAMETER_LOGIN = "login";
    private final static String PARAMETER_PASSWORD = "password";
    private final static String PARAMETER_FIRST_NAME = "first_name";
    private final static String PARAMETER_SECOND_NAME = "second_name";
    private final static String PARAMETER_EMAIL = "second_name";


    @Override
    public ResponseContent process(HttpServletRequest request) {

        User user = new User(
                request.getParameter(PARAMETER_LOGIN),
                request.getParameter(PARAMETER_PASSWORD),
                request.getParameter(PARAMETER_FIRST_NAME),
                request.getParameter(PARAMETER_SECOND_NAME),
                request.getParameter(PARAMETER_EMAIL)
        );

        ServiceUser serviceUser = new ServiceUserImpl();
        boolean isStartSignUp = serviceUser.register(user);

        ResponseContent responseContent = new ResponseContent();

        if (isStartSignUp) {
            responseContent.setRouter(new Router(PageDirector.ACCOUNT, Router.Type.FORWARD));
        } else {
            responseContent.setRouter(new Router(PageDirector.REGISTRATION, Router.Type.FORWARD));
        }

        return responseContent;
    }
}
