package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyAlbumCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        MusicService musicService = new MusicServiceImpl();
        AlbumService albumService = new AlbumServiceImpl();

        request.setAttribute("success", musicService.buyAlbum(request));
        request.setAttribute("albumDtoList", albumService.getAllNotUserAlbumDto(request));
        request.setAttribute("albumName", request.getParameter("albumName"));

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_ALBUM_SHOP.getValue(), Router.Type.REDIRECT));

        return responseContent;
    }
}
