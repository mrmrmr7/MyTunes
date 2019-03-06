package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.service.impl.ServiceUserImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandLogOut implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.LOGOUT.getValue() + " command in detected");

        ResponseContent responseContent = new ResponseContent();

        ServiceUserImpl serviceUser = new ServiceUserImpl();

        try {
            serviceUser.logout(request.getSession(false));
        } catch (ServiceException e) {
            System.out.println("Fail to log out");
        }

        responseContent.setRouter(new Router(PageDirector.LANDING.getValue(), Router.Type.REDIRECT));

        return responseContent;
    }
}
