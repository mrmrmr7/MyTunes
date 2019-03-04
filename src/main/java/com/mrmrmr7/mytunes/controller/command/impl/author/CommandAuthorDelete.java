package com.mrmrmr7.mytunes.controller.command.impl.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandAuthorDelete implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("DeleteAuthor command detected");

        AuthorDAO authorDAO = new AuthorDAO();

        try {
            authorDAO.delete(Integer.valueOf(request.getParameter("id")));
        } catch (DAOException e) {
            System.out.println("Impossible to find author with such id");
        }

        authorDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/author.jsp", "redirect"));
        return responseContent;
    }
}
