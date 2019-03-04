package com.mrmrmr7.mytunes.controller.command.impl.role;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.RoleDAO;
import com.mrmrmr7.mytunes.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CommandRoleGetAll implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAllRoles command detected");
        RoleDAO roleDAO = new RoleDAO();
        List<Role> roleList = null;
        try {
            roleList = roleDAO.getAll();
        } catch (DAOException e) {
            System.out.println("Impossible to find role with such id");
        }
        roleDAO.destroy();
        request.setAttribute("roleList", roleList);
        System.out.println(roleList);
        request.setAttribute("viewName", "role/getall");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
