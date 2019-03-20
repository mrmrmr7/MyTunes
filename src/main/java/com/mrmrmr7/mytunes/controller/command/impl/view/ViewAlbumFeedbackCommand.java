package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumFeedbackDtoService;
import com.mrmrmr7.mytunes.service.AlbumFeedbackService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumFeedbackDtoServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AlbumFeedbackServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewAlbumFeedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_ALBUM_FEEDBACK_TABLE.getValue() + " command detected");

        AlbumFeedbackService albumFeedbackDtoService = new AlbumFeedbackServiceImpl();

        try {

            request.setAttribute("albumFeedbackDtoList", albumFeedbackDtoService.getFeedbackDtoByAlbumId(request));
            request.setAttribute("albumName", request.getParameter("albumName"));
            request.setAttribute("success", true);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_ALBUM_FEEDBACK, Router.Type.FORWARD));

        return responseContent;
    }
}
