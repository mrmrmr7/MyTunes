package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.MusicSelectionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AuthorServiceImpl;
import com.mrmrmr7.mytunes.service.impl.CompositionServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicSelectionServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminUploadCompositionToMusicSelectionCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.ADMIN_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION.getValue() + " command detected");

        MusicSelectionService musicSelectionService = new MusicSelectionServiceImpl();

        try {
            request.setAttribute("success", musicSelectionService.addCompositionToMusicSelection(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
