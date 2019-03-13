package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class BuyAlbumCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.BUY_ALBUM.getValue() + " command detected");

        MusicService musicService = new MusicServiceImpl();

        try {
            request.setAttribute("musicType", "album");
            request.setAttribute("success", musicService.buyAlbum(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.ALBUM_SHOP, Router.Type.FORWARD));

        return responseContent;
    }
}