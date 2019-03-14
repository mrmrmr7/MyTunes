package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.REDIRECT));
        return responseContent;
    }
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        System.out.println(CommandDirector.LOG_OUT.getValue() + " command in detected");

        ResponseContent responseContent = new ResponseContent();

        UserServiceImpl serviceUser = new UserServiceImpl();

        try {
            serviceUser.logout(request, httpServletResponse);
        } catch (ServiceException e) {
            System.out.println("Fail to log out");
        }

        responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.REDIRECT));

        return responseContent;
    }


}
