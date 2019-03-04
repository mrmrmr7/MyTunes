package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDAO;
import com.mrmrmr7.mytunes.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandRoleGetById implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetRole command detected");
        RoleDAO roleDAO = new RoleDAO();
        Role role = null;
        try {
            role = roleDAO.getByPK(Integer.valueOf(request.getParameter("id"))).get();
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        }
        roleDAO.destroy();
        request.setAttribute("role", role);
        request.setAttribute("viewName", "role/getbyid");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
