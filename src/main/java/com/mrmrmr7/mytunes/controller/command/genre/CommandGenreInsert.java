package com.mrmrmr7.mytunes.controller.command.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.GenreDAO;
import com.mrmrmr7.mytunes.entity.Genre;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandGenreInsert implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("InsertGenre command detected");

        Genre genre = new Genre(
                request.getParameter("genreGenre")
        );

        GenreDAO genreDAO = new GenreDAO();

        try {
            genreDAO.insert(genre);
        } catch (SQLException e) {
            System.out.println("Impossible to find genre with such id");
        }
        genreDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/genre.jsp", "redirect"));
        return responseContent;
    }
}
