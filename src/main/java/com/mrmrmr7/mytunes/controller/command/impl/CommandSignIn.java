package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.ServiceUserImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandSignIn implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.SIGN_IN.getValue() + " command in detected");

        String login = request.getParameter(CommandDirector.LOGIN.getValue());
        String password = request.getParameter(CommandDirector.PASSWORD.getValue());

        boolean isSignIn = false;

        try {
            ServiceUserImpl serviceUser = new ServiceUserImpl();
            isSignIn = serviceUser.login(login, password, request.getSession(false));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        if (isSignIn) {
            responseContent.setRouter(new Router(PageDirector.ACCOUNT.getValue(), Router.Type.FORWARD));
        } else {
            responseContent.setRouter(new Router(PageDirector.LANDING.getValue(), Router.Type.REDIRECT));
        }

        return responseContent;
    }
}
