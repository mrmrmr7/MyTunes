package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.MusicSelectionFeedbackDtoService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.MusicSelectionFeedbackDtoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewMusicSelectionFeedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_TABLE.getValue() + " command detected");

        MusicSelectionFeedbackDtoService musicSelectionFeedbackDtoService = new MusicSelectionFeedbackDtoServiceImpl();

        try {
            request.setAttribute("musicSelectionFeedbackDtoList", musicSelectionFeedbackDtoService.getFeedbackByMusicSelectionId(request));
            request.setAttribute("musicSelectionName", request.getParameter("musicSelectionName"));
            request.setAttribute("success", true);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_MUSIC_SELECTION_FEEDBACK, Router.Type.FORWARD));

        return responseContent;
    }
}
