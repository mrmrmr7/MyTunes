package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.MusicSelectionService;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.MusicSelectionServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class BuyMusicSelectionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.BUY_MUSIC_SELECTION.getValue() + " command detected");

        MusicService musicService = new MusicServiceImpl();
        MusicSelectionService musicSelectionService = new MusicSelectionServiceImpl();

        try {
            request.setAttribute("success", musicService.buyMusicSelection(request));
            request.setAttribute("musicSelectionInfoList", musicSelectionService.getAllNotUserMusicSelectionInfo(request));
            request.setAttribute("musicSelectionName", request.getParameter("musicSelectionName"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.MUSIC_SELECTION_SHOP, Router.Type.FORWARD));

        return responseContent;
    }
}
