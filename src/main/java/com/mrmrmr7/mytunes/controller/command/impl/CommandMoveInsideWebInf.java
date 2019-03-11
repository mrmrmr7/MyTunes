package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.CommandExtended;
import com.mrmrmr7.mytunes.controller.command.WebInfProvider;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandMoveInsideWebInf implements CommandExtended {


    private static final String PARAMETER_PATH = "path";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.MOVE_INSIDE_WEB_INF.getValue() + " detected");

        String path = request.getParameter(PARAMETER_PATH);

        String avaliablePath = WebInfProvider.getInstance().takePath(path);

        System.out.println(avaliablePath);

        ResponseContent responseContent = new ResponseContent();

        if (avaliablePath != null) {
            responseContent.setRouter(new Router(PageDirector.getEnum(avaliablePath), Router.Type.FORWARD));
        } else {
            responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.REDIRECT));
        }

        return responseContent;
    }

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        return null;
    }
}
