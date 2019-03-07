package com.mrmrmr7.mytunes.controller.command.impl.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandUserGetAll implements Command {
    private final static String ATTRIBUTE_USER_LIST = "userList";
    private final static String INCLUDE_PAGE = "user/getall";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.USER_GET_ALL.getValue() + " command detected");

        UserDAO userDAO = new UserDAO();
        List<User> userList = null;
        try {
            userDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            userList = userDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find user with such id");
        } finally {
            userDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_USER_LIST, userList);
        request.setAttribute(PageDirector.VIEW_NAME.getValue(), INCLUDE_PAGE);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
