package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDAO;
import com.mrmrmr7.mytunes.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandRoleInsert implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("InsertRole command detected");

        Role role = new Role(
                request.getParameter("roleRole")
        );

        RoleDAO roleDAO = new RoleDAO();

        try {
            roleDAO.insert(role);
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        }
        roleDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/role.jsp", "redirect"));
        return responseContent;
    }
}
