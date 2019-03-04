package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDAO;
import com.mrmrmr7.mytunes.entity.Album;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumGetById implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAlbum command detected");
        AlbumDAO AlbumDAO = new AlbumDAO();
        Album Album = null;
        try {
            Album = AlbumDAO.getByPK(Integer.valueOf(request.getParameter("id"))).get();
        } catch (DAOException e) {
            System.out.println("Impossible to find Album with such id");
        }
        AlbumDAO.destroy();
        request.setAttribute("Album", Album);
        request.setAttribute("viewName", "Album/getbyid");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
