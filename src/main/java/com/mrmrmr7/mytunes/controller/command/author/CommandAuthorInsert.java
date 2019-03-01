package com.mrmrmr7.mytunes.controller.command.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;
import com.mrmrmr7.mytunes.entity.Author;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandAuthorInsert implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("InsertAuthor command detected");

        Author author = new Author(
                request.getParameter("authorFirstName"),
                request.getParameter("authorSecondName"),
                request.getParameter("authorPseudonim")
        );

        AuthorDAO authorDAO = new AuthorDAO();

        try {
            authorDAO.insert(author);
        } catch (SQLException e) {
            System.out.println("Impossible to find author with such id");
        }
        authorDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/author.jsp", "redirect"));
        return responseContent;
    }
}
