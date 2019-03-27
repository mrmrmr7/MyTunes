package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.exception.CommandException;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.UserService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartRestartPasswordCmmand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws CommandException {
        String email = request.getParameter("email");

        boolean isStartRestart = false;
        UserService userService = new UserServiceImpl();

        if (email != null) {
            try {
                isStartRestart = userService.tryRestartPassword(email);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        ResponseContent responseContent = new ResponseContent();
        if (isStartRestart) {
            httpServletResponse.addCookie(new Cookie("resetStart", String.valueOf(true)));
            responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), Router.Type.REDIRECT));
        } else {
            httpServletResponse.addCookie(new Cookie("falseEmail", String.valueOf(true)));
            responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_FORGET_PASSWORD_PAGE.getValue(), Router.Type.REDIRECT));
        }
        return  responseContent;
    }
}
