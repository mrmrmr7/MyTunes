package com.mrmrmr7.mytunes.controller.command.impl.author;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.AuthorDao;
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

        AuthorDao authorDAO = new AuthorDao();

        try {
            authorDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            authorDAO.insert(author);
        } catch (DaoException e) {
            System.out.println("Impossible to find author with such id");
        } finally {
            authorDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_AUTHOR,Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
