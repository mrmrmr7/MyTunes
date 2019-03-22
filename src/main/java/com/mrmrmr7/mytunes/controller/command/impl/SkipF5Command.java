package com.mrmrmr7.mytunes.controller.command.impl;


import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class SkipF5Command implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        PageDirector pageDirector = PageDirector.getEnum(request.getParameter("from"));
        responseContent.setRouter(new Router(pageDirector, Router.Type.FORWARD));
        return responseContent;
    }
}