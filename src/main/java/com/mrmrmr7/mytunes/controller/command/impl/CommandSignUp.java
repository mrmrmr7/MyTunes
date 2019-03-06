package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceSignUp;
import com.mrmrmr7.mytunes.service.impl.ServiceSignUpImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandSignUp implements Command {
    private final static String PARAMETER_LOGIN = "login";
    private final static String PARAMETER_PASSWORD = "password";
    private final static String PARAMETER_FIRST_NAME = "first_name";
    private final static String PARAMETER_SECOND_NAME = "second_name";
    private final static String PARAMETER_EMAIL = "second_name";


    @Override
    public ResponseContent process(HttpServletRequest request) throws Exception {
        ServiceSignUp serviceSignUp = new ServiceSignUpImpl();

        User user = new User(
                request.getParameter(PARAMETER_LOGIN),
                request.getParameter(PARAMETER_PASSWORD),
                request.getParameter(PARAMETER_FIRST_NAME),
                request.getParameter(PARAMETER_SECOND_NAME),
                request.getParameter(PARAMETER_EMAIL)
        );

        serviceSignUp.execute(user);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.ACCOUNT.getValue(),
                        Router.Type.FORWARD
                )
        );
        return responseContent;
    }
}
