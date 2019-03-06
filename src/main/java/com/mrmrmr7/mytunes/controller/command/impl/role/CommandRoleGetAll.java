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
import java.util.List;

public class CommandRoleGetAll implements Command {
    private final static String ATTRIBUTE_ROLE_LIST = "roleList";
    private final static String INCLUDE_PATH = "role/getall";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ROLE_GET_ALL.getValue() + " command detected");
        RoleDAO roleDAO = new RoleDAO();
        List<Role> roleList = null;
        try {
            roleDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            roleList = roleDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        } finally {
            roleDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_ROLE_LIST, roleList);
        request.setAttribute(PageDirector.VIEW.getValue(), INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        PageDirector.VIEW.getValue(),
                        Router.Type.FORWARD
                )
        );
        return responseContent;
    }
}
