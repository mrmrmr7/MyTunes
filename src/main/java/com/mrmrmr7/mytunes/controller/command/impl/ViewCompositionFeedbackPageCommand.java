package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.CompositionFeedbackService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.CompositionFeedbackServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewCompositionFeedbackPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_COMPOSITION_FEEDBACK_PAGE.getValue() + " command detected");

        CompositionFeedbackService compositionFeedbackDtoService = new CompositionFeedbackServiceImpl();
        try {
            request.setAttribute("userCompositionFeedbackList", compositionFeedbackDtoService.getUserCompositionFeedbackList(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_COMPOSITION_FEEDBACK, Router.Type.FORWARD));
        return responseContent;
    }
}
