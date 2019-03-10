package com.mrmrmr7.mytunes.controller.command.impl.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.GenreDao;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandGenreInsert implements Command {
    private final static String PARAMETER_GENRE = "genreGenre";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.GENRE_INSERT.getValue() + " command detected");

        Genre genre = new Genre(
                request.getParameter(PARAMETER_GENRE)
        );

        GenreDao genreDAO = new GenreDao();

        try {
            genreDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            genreDAO.insert(genre);
        } catch (DaoException e) {
            System.out.println("Impossible to find genre with such id");
        } finally {
            genreDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_GENRE,Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
