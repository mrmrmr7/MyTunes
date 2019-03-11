package com.mrmrmr7.mytunes.controller.command.impl.album;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.AlbumDao;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.util.BeanDirector;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumGetById implements Command {

    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String INCLUDE_PATH = "album/getbyid";
    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_GET_BY_ID.getValue() + " command detected");
        AlbumDao albumDAO = new AlbumDao();
        Album album = null;
        try {
            albumDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            album = albumDAO.getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID))).get();
        } catch (DaoException e) {
            System.out.println("Impossible to find Album with such id");
        } finally {
            albumDAO.closeConnection();
        }
        
        request.setAttribute(BeanDirector.ALBUM.getValue(), album);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW, Router.Type.FORWARD));
        return responseContent;
    }
}
