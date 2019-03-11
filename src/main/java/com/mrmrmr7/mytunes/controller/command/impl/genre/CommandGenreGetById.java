package com.mrmrmr7.mytunes.controller.command.impl.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.GenreDao;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.util.BeanDirector;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandGenreGetById implements Command {
    private final static String ATTRIBUTE_VIEW_NAME = "viewName";
    private final static String INCLUDE_PATH = "genre/getbyid";
    private final static String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.GENRE_GET_BY_ID.getValue() + " command detected");
        GenreDao genreDAO = new GenreDao();
        Genre genre = null;
        try {
            genreDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            genre = genreDAO
                    .getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID)))
                    .get();
        } catch (DaoException e) {
            System.out.println("Impossible to find genre with such id");
        } finally {
            genreDAO.closeConnection();
        }

        request.setAttribute(BeanDirector.GENRE.getValue(), genre);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
