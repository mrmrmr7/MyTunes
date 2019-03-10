package com.mrmrmr7.mytunes.controller.command.impl.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.UserDao;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.BeanDirector;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandUserGetById implements Command {
    private final static String PARAMETER_ID = "id";
    private final static String INCLUDE_PATH = "user/getbyid.jsp";
    private final static String VIEW_NAME = "viewName";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.USER_GET_BY_ID.getValue() + " command detected");
        User user = null;
        UserDao userDAO = new UserDao();
        try {
            userDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            user = userDAO
                    .getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID)))
                    .get();
        } catch (DaoException e) {
            System.out.println("Impossible to find user with such id");
        } finally {
            userDAO.closeConnection();
        }

        request.setAttribute(BeanDirector.USER.getValue(), user);
        request.setAttribute(VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    } 
}
