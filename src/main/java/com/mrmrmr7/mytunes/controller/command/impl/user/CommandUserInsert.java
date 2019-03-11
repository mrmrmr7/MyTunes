package com.mrmrmr7.mytunes.controller.command.impl.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.UserDao;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandUserInsert implements Command {
    private final static String PARAMETER_LOGIN = "login";
    private final static String PARAMETER_PASSWORD = "password";
    private final static String PARAMETER_FIRST_NAME = "firstName";
    private final static String PARAMETER_SECOND_NAME = "secondName";
    private final static String PARAMETER_EMAIL = "email";
    private final static String PARAMETER_BALANCE = "balance";
    private final static String PARAMETER_SALE = "sale";
    private final static String PARAMETER_ROLE_ID = "roleId";
    private final static String PARAMETER_STATUS_ID = "statusId";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.USER_INSERT.getValue() + " command detected");

        User user = new User(
                request.getParameter(PARAMETER_LOGIN),
                request.getParameter(PARAMETER_PASSWORD),
                request.getParameter(PARAMETER_FIRST_NAME),
                request.getParameter(PARAMETER_SECOND_NAME),
                request.getParameter(PARAMETER_EMAIL),
                Long.valueOf(request.getParameter(PARAMETER_BALANCE)),
                Byte.valueOf(request.getParameter(PARAMETER_SALE)),
                Byte.valueOf(request.getParameter(PARAMETER_ROLE_ID)),
                Byte.valueOf(request.getParameter(PARAMETER_STATUS_ID))
        );

        UserDao userDAO = new UserDao();
        try {
            userDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            userDAO.insert(user);
            userDAO.closeConnection();
        } catch (DaoException e) {
            System.out.println("Impossible to find user with such id");
        } finally {
            userDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.CRUD_USER,Router.Type.REDIRECT));
        return responseContent;
    }
}
