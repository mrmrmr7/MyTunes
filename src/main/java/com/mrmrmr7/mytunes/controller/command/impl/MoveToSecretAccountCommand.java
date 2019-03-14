package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.CommandExtended;
import com.mrmrmr7.mytunes.controller.command.WebInfProvider;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveToSecretAccountCommand implements CommandExtended {


    private static final String PARAMETER_PATH = "path";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.SECRET_ACCOUNT.getValue() + " detected");


        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.SECRET_ACCOUNT, Router.Type.FORWARD));

        return responseContent;
    }

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        return null;
    }
}
