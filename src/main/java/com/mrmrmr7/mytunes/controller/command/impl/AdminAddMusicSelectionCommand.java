package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.GenreService;
import com.mrmrmr7.mytunes.service.MusicSelectionInfoService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AuthorServiceImpl;
import com.mrmrmr7.mytunes.service.impl.GenreServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicSelectionInfoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAddMusicSelectionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ADMIN_ADD_MUSIC_SELECTION.getValue() + " command detected");

        MusicSelectionInfoService musicSelectionInfoService = new MusicSelectionInfoServiceImpl();

        try {
            request.setAttribute("success", musicSelectionInfoService.insertMusicSelection(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_CREATE_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
