package com.mrmrmr7.mytunes.controller.command.impl.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class CommandUserDelete implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("DeleteUser command detected");

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.delete(Integer.valueOf(request.getParameter("id")));
        } catch (DAOException e) {
            System.out.println("Impossible to find user with such id");
        }
        userDAO.destroy();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(request.getContextPath() + "/jsp/crud/user.jsp", "redirect"));
        return responseContent;
    }
}
