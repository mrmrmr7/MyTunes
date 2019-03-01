package com.mrmrmr7.mytunes.controller.command.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;
import com.mrmrmr7.mytunes.entity.Author;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CommandAuthorGetAll implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAllAuthors command detected");
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authorList = null;
        try {
            authorList = authorDAO.getAll();
        } catch (SQLException e) {
            System.out.println("Impossible to find author with such id");
        }
        authorDAO.destroy();
        request.setAttribute("authorList", authorList);
        System.out.println(authorList);
        request.setAttribute("viewName", "author/getall");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
