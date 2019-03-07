package com.mrmrmr7.mytunes.controller.command.impl.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.GenreDAO;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandGenreGetAll implements Command {
    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String ATTRIBUTE_GENRE_LIST = "genreList";
    private static final String INCLUDE_PATH = "genre/getall";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.GENRE_GET_ALL.getValue() + " command detected");
        GenreDAO genreDAO = new GenreDAO();
        List<Genre> genreList = null;
        try {
            genreDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            genreList = genreDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find genre with such id");
        } finally {
            genreDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_GENRE_LIST, genreList);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
