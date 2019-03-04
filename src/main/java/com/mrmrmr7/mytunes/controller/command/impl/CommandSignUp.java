package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceSignUp;
import com.mrmrmr7.mytunes.service.impl.ServiceSignUpImpl;

import javax.servlet.http.HttpServletRequest;

public class CommandSignUp implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) throws Exception {
        ServiceSignUp serviceSignUp = new ServiceSignUpImpl();

        User user = new User(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("first_name"),
                request.getParameter("second_name"),
                request.getParameter("email")
        );

        serviceSignUp.execute(user);


        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/account.jsp", "forward"));
        return responseContent;
    }
}
