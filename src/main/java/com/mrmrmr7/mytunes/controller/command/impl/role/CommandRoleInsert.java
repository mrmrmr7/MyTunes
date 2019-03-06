package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDAO;
import com.mrmrmr7.mytunes.entity.Role;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandRoleInsert implements Command {
    private final static String PARAMETER_ROLE = "roleRole";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ROLE_INSERT.getValue() + " command detected");

        Role role = new Role(
                request.getParameter(PARAMETER_ROLE)
        );

        RoleDAO roleDAO = new RoleDAO();
        try {
            roleDAO.setConnection(ConnectionPoolFactory
                .getInstance()
                .getConnectionPool(ConnectionPoolType.MYSQL)
                .getConnection());

            roleDAO.insert(role);
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        } finally {
            roleDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.CRUD_ROLE.getValue(),
                        Router.Type.REDIRECT
                )
        );
        return responseContent;
    }
}