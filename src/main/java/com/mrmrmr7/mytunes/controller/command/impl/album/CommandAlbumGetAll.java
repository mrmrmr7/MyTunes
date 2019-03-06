package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDAO;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandAlbumGetAll implements Command {

    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String INCLUDE_PATH = "album/getall";
    private static final String ATTRIBUTE_ALBUM_LIST = "albumList";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_GET_ALL.getValue() + " command detected");
        AlbumDAO albumDAO = new AlbumDAO();
        List<Album> albumList = null;
        try {
            albumDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumList = albumDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find album with such id");
        } finally {
            albumDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_ALBUM_LIST, albumList);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.VIEW.getValue(),
                        Router.Type.FORWARD
                )
        );
        return responseContent;
    }
}