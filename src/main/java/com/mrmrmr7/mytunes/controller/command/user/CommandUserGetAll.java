package com.mrmrmr7.mytunes.controller.command.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CommandUserGetAll implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetAll command detected");
        UserDAO userDAO = new UserDAO();
        List<User> userList = null;
        try {
            userList = userDAO.getAll();
        } catch (SQLException e) {
            System.out.println("Impossible to find user with such id");
        }
        userDAO.destroy();
        request.setAttribute("userList", userList);
        request.setAttribute("viewName", "user/getall");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    }
}
