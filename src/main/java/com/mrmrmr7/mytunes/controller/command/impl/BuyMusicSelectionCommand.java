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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyMusicSelectionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        MusicService musicService = new MusicServiceImpl();
        MusicSelectionService musicSelectionService = new MusicSelectionServiceImpl();

        request.setAttribute("musicSelectionInfoList", musicSelectionService.getAllNotUserMusicSelectionInfo(request));

        if (musicService.buyMusicSelection(request)) {
            response.addCookie(new Cookie("success", String.valueOf(true)));
            response.addCookie(new Cookie("musicSelectionName", request.getParameter("musicSelectionName")));
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_MUSIC_SELECTION_SHOP.getValue(), Router.Type.REDIRECT));

        return responseContent;
    }
}
