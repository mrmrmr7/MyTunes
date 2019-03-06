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

public class CommandAuthorInsert implements Command {
    private static final String PARAMETER_FIRST_NAME = "authorFirstName";
    private static final String PARAMETER_SECOND_NAME = "authorSecondName";
    private static final String PARAMETER_PSEUDONIM = "authorPseudonim";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.AUTHOR_INSERT.getValue() + " command detected");

        Author author = new Author(
                request.getParameter(PARAMETER_FIRST_NAME),
                request.getParameter(PARAMETER_SECOND_NAME),
                request.getParameter(PARAMETER_PSEUDONIM)
        );

        AuthorDAO authorDAO = new AuthorDAO();

        try {
            authorDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            authorDAO.insert(author);
        } catch (DAOException e) {
            System.out.println("Impossible to find author with such id");
        } finally {
            authorDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.CRUD_AUTHOR.getValue(),
                        Router.Type.REDIRECT
                )
        );
        return responseContent;
    }
}
