package com.mrmrmr7.mytunes.controller.command;



import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request);
    default ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.REDIRECT));
        return responseContent;
    };
}