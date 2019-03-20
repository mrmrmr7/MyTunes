package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.MusicSelectionFeedbackService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.MusicSelectionFeedbackServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class AddMusicSelectionFeedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ADD_MUSIC_SELECTION_FEEDBACK.getValue() + " command detected");

        MusicSelectionFeedbackService musicSelectionFeedbackDtoService = new MusicSelectionFeedbackServiceImpl();

        try {
            request.setAttribute("musicSelectionName", request.getParameter("musicSelectionName"));
            request.setAttribute("success", musicSelectionFeedbackDtoService.addMusicSelectionFeedback(request));
            request.setAttribute("userMusicSelectionFeedbackList", musicSelectionFeedbackDtoService.getUserMusicSelectionFeedbackList(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_MUSIC_SELECTION_FEEDBACK, Router.Type.FORWARD));

        return responseContent;
    }
}
