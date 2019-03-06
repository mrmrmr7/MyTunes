package com.mrmrmr7.mytunes.controller.command.impl.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CommandAuthorGetAll implements Command {

    private static final String ATTRIBUTE_AUTHOR_LIST = "authorList";
    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String INSERT_PAGE = "author/getall";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.AUTHOR_GET_ALL.getValue() + " command detected");
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authorList = null;
        try {
            authorDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            authorList = authorDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find author with such id");
        } finally {
            authorDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_AUTHOR_LIST, authorList);
        System.out.println(authorList);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INSERT_PAGE);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.VIEW.toString(),
                        Router.Type.FORWARD
                )
        );
        return responseContent;
    }
}
