package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDao;
import com.mrmrmr7.mytunes.entity.Role;
import com.mrmrmr7.mytunes.util.BeanDirector;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandRoleGetById implements Command {
    private final static String PARAMETER_ID = "id";
    private final static String INCLUDE_PATH = "role/getbyid";
    private final static String VIEW_NAME = "viewName";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ROLE_GET_BY_ID.getValue() + " command detected");

        RoleDao roleDAO = new RoleDao();
        Role role = null;
        try {
            roleDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            role = roleDAO
                    .getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID)))
                    .get();
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        } finally {
            roleDAO.closeConnection();
        }

        request.setAttribute(BeanDirector.ROLE.getValue(), role);
        request.setAttribute(VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
