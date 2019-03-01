package com.mrmrmr7.mytunes.controller.command.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.GenreDAO;
import com.mrmrmr7.mytunes.entity.Genre;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandGenreGetById implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetGenre command detected");
        GenreDAO genreDAO = new GenreDAO();
        Genre genre = null;
        try {
            genre = genreDAO.getByPK(Integer.valueOf(request.getParameter("id"))).get();
        } catch (SQLException e) {
            System.out.println("Impossible to find genre with such id");
        }
        genreDAO.destroy();
        request.setAttribute("genre", genre);
        request.setAttribute("viewName", "genre/getbyid");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
