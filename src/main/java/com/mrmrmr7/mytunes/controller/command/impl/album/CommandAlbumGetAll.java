package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDAO;
import com.mrmrmr7.mytunes.entity.Album;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandAlbumGetAll implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAllAlbums command detected");
        AlbumDAO albumDAO = new AlbumDAO();
        List<Album> albumList = null;
        try {
            albumList = albumDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find album with such id");
        }
        albumDAO.destroy();
        request.setAttribute("albumList", albumList);
        System.out.println(albumList);
        request.setAttribute("viewName", "album/getall");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
