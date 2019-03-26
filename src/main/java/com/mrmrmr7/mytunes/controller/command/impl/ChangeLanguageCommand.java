package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.CHANGE_LANGUAGE_COMMAND.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();
        request.setAttribute("lang", request.getParameter("lang"));
        Cookie cookie = new Cookie("locale", request.getParameter("lang"));
        response.addCookie(cookie);
        String from = request.getParameter("from");
        responseContent.setRouter(new Router(from, Router.Type.FORWARD));
        return responseContent;
    }
}
