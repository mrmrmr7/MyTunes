package com.mrmrmr7.mytunes.controller.command.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CommandUserInsert implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("InsertUser command detected");
        User user = new User(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("firstName"),
                request.getParameter("secondName"),
                request.getParameter("email"),
                Long.valueOf(request.getParameter("balance")),
                Byte.valueOf(request.getParameter("sale")),
                Byte.valueOf(request.getParameter("roleId")),
                Byte.valueOf(request.getParameter("statusId"))
        );

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.insert(user);
        } catch (SQLException e) {
            System.out.println("Impossible to find user with such id");
        }
        userDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/user.jsp", "redirect"));
        return responseContent;
    }
}
