package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDao;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandRoleDelete implements Command {
    private final static String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ROLE_DELETE.getValue() + " command detected");

        RoleDao roleDAO = new RoleDao();
        try {
            roleDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            roleDAO.delete(Integer.valueOf(request.getParameter(PARAMETER_ID)));
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        } finally {
            roleDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_ROLE,Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
