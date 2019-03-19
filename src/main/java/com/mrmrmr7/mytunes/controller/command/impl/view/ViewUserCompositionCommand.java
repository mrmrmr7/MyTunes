package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserCompositionService;
import com.mrmrmr7.mytunes.service.impl.UserCompositionServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewUserCompositionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_CURRENT_BALANCE_PAGE.getValue() + " command detected");

        UserCompositionService userCompositionService = new UserCompositionServiceImpl();

        try {
            request.setAttribute("userCompositionList", userCompositionService.showUserComposition(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.BALANCE_CURRENT, Router.Type.FORWARD));

        return responseContent;
    }
}
