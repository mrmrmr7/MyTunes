package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandRoleDelete implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("DeleteRole command detected");

        RoleDAO roleDAO = new RoleDAO();

        try {
            roleDAO.delete(Integer.valueOf(request.getParameter("id")));
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        }

        roleDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/role.jsp", "redirect"));
        return responseContent;
    }
}
