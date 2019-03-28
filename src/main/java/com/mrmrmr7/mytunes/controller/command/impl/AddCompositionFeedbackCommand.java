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
import javax.servlet.http.HttpServletResponse;

public class AddCompositionFeedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CompositionFeedbackService compositionFeedbackDtoService = new CompositionFeedbackServiceImpl();

        request.setAttribute("compositionName", request.getParameter("compositionName"));
        request.setAttribute("success", compositionFeedbackDtoService.addCompositionFeedback(request));
        request.setAttribute("userCompositionFeedbackList", compositionFeedbackDtoService.getUserCompositionFeedbackList(request));


        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_COMPOSITION_FEEDBACK, Router.Type.FORWARD));

        return responseContent;
    }
}
