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
import java.util.List;
import java.util.Optional;

public class GetMusicSelectionForUpdateCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ADMIN_GET_MUSIC_SELECTION_FOR_UPDATE.getValue() + " command detected");

        MusicSelectionService musicSelectionService = new MusicSelectionServiceImpl();

        String name = request.getParameter("musicSelectionNameToGet");

        try {
            Optional<MusicSelectionInfo> musicSelectionInfoOptional = musicSelectionService.getMusicSelectionInfoByName(name);

            request.setAttribute("musicSelectionInfo", musicSelectionInfoOptional.get());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("showInf", true);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPDATE_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
