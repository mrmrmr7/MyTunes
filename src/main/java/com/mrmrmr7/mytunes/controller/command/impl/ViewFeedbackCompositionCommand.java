package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.CompositionDtoService;
import com.mrmrmr7.mytunes.service.CompositionFeedbackDtoService;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.CompositionDtoServiceImpl;
import com.mrmrmr7.mytunes.service.impl.CompositionFeedbackDtoServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewFeedbackCompositionCommand  implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_FEEDBACK_COMPOSITION.getValue() + " command detected");

        CompositionFeedbackDtoService compositionFeedbackDtoService = new CompositionFeedbackDtoServiceImpl();

        try {

            request.setAttribute("compositionFeedbackDtoList", compositionFeedbackDtoService.getFeedbackByCompositionId(request));
            request.setAttribute("compositionName", request.getParameter("compositionName"));
            request.setAttribute("success", true);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_COMPOSITION_FEEDBACK, Router.Type.FORWARD));

        return responseContent;
    }
}
