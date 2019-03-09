package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDao;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumInsert implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_INSERT + " command detected");

        Album album = new Album(
                request.getParameter("albumDescription"),
                Long.valueOf(request.getParameter("albumPrice")),
                Integer.valueOf(request.getParameter("albumAuthor_id")),
                Integer.valueOf(request.getParameter("albumGenre_id"))
        );

        AlbumDao albumDAO = new AlbumDao();

        try {
            albumDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumDAO.insert(album);
        } catch (DAOException e) {
            System.out.println("Impossible to find album with such id");
        } finally {
            albumDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_ALBUM, Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
