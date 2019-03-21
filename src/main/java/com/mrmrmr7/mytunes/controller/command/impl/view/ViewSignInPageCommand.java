package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewSignInPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_SIGN_IN_PAGE.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.SIGN_IN, Router.Type.FORWARD));
        return responseContent;
    }
}
