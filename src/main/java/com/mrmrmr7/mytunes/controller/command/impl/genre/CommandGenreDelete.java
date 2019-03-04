package com.mrmrmr7.mytunes.controller.command.impl.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.GenreDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandGenreDelete implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("DeleteGenre command detected");

        GenreDAO genreDAO = new GenreDAO();

        try {
            genreDAO.delete(Integer.valueOf(request.getParameter("id")));
        } catch (DAOException e) {
            System.out.println("Impossible to find genre with such id");
        }

        genreDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/genre.jsp", "redirect"));
        return responseContent;
    }
}
