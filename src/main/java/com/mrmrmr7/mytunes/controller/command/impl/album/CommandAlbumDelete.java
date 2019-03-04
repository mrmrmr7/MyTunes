package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandAlbumDelete implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("DeleteAlbum command detected");

        AlbumDAO albumDAO = new AlbumDAO();

        try {
            albumDAO.delete(Integer.valueOf(request.getParameter("id")));
        } catch (DAOException e) {
            System.out.println("Impossible to find album with such id");
        }

        albumDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/album.jsp", "redirect"));
        return responseContent;
    }
}
