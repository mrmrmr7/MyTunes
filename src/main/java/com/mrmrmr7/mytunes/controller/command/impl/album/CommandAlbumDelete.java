package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDao;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumDelete implements Command {

    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_DELETE.getValue() + " command detected");

        AlbumDao albumDAO = new AlbumDao();

        try {
            albumDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumDAO.delete(Integer.valueOf(request.getParameter(PARAMETER_ID)));
        } catch (DAOException e) {
            System.out.println("Impossible to find album with such id");
        } finally {
            albumDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.CRUD_ALBUM,Router.Type.REDIRECT));
        return responseContent;
    }
}
