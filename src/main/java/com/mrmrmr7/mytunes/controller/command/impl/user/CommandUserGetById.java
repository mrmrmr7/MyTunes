package com.mrmrmr7.mytunes.controller.command.impl.user;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;

import javax.servlet.http.HttpServletRequest;

public class CommandUserGetById implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("GetUser command detected");
        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.getByPK(Integer.valueOf(request.getParameter("id"))).get();
        } catch (DAOException e) {
            System.out.println("Impossible to find user with such id");
        }
        userDAO.destroy();
        request.setAttribute("user", user);
        request.setAttribute("viewName", "user/getbyid");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/view.jsp", "forward"));
        return responseContent;
    } 
}
