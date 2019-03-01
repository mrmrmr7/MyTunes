package com.mrmrmr7.mytunes.controller.command.genre;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.GenreDAO;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CommandGenreGetAll implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAllGenres command detected");
        GenreDAO genreDAO = new GenreDAO();
        List<Genre> genreList = null;
        try {
            genreList = genreDAO.getAll();
        } catch (SQLException e) {
            System.out.println("Impossible to find genre with such id");
        }
        genreDAO.destroy();
        request.setAttribute("genreList", genreList);
        System.out.println(genreList);
        request.setAttribute("viewName", "genre/getall");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
