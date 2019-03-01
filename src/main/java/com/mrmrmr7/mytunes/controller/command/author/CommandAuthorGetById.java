package com.mrmrmr7.mytunes.controller.command.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;
import com.mrmrmr7.mytunes.entity.Author;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandAuthorGetById implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAuthor command detected");
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = null;
        try {
            author = authorDAO.getByPK(Integer.valueOf(request.getParameter("id"))).get();
        } catch (SQLException e) {
            System.out.println("Impossible to find author with such id");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        authorDAO.destroy();
        request.setAttribute("author", author);
        request.setAttribute("viewName", "author/getbyid");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
