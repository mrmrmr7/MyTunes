package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandMoveInsideWebInf implements Command {


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
}
