package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.controller.command.exception.CommandException;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.service.impl.UserDtoServiceImpl;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SignInCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws CommandException {
        System.out.println(CommandDirector.SIGN_IN.getValue() + " command in detected");
        ResponseContent responseContent = new ResponseContent();

        try {
            String login = request.getParameter(RequestDirector.LOGIN.getValue());
            String password = request.getParameter(RequestDirector.PASSWORD.getValue());

            UserServiceImpl userService = new UserServiceImpl();
            boolean isSignIn = userService.isRightUser(login, password);
            if (isSignIn) {
                UserDtoServiceImpl userDtoServiceImpl = new UserDtoServiceImpl();
                userDtoServiceImpl.setDtoByLogin(login, request);
                responseContent.setRouter(new Router(PageDirector.ACCOUNT, Router.Type.FORWARD));
                Map<String, Cookie> cookieMap = userService.getCookies(login);
                httpServletResponse.addCookie(cookieMap.get(RequestDirector.TOKEN.getValue()));
                httpServletResponse.addCookie(cookieMap.get(RequestDirector.PUBLIC_KEY.getValue()));
            } else {
                request.setAttribute("failSignIn", true);
                responseContent.setRouter(new Router(PageDirector.SIGN_IN, Router.Type.FORWARD));
            }
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage() + ":" + ExceptionDirector.CMD_SIN_PRC.getValue());
        }

        return responseContent;
    }

    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.FORWARD));
        return responseContent;
    }
}
