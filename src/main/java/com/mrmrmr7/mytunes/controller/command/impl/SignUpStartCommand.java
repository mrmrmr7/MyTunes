package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.builder.Builder;
import com.mrmrmr7.mytunes.builder.BuilderFactory;
import com.mrmrmr7.mytunes.builder.exception.BuilderException;
import com.mrmrmr7.mytunes.builder.impl.UserBuilder;
import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.SignUpService;
import com.mrmrmr7.mytunes.service.impl.SignUpServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignUpStartCommand implements Command {


    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.SIGN_UP.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();

        Builder userBuilder = BuilderFactory.getInstance().getBuilder(User.class);

        User user = null;

        try {
            user = (User)userBuilder.build(request);
        } catch (BuilderException e) {
            request.setAttribute("invalidData", true);
            responseContent.setRouter(new Router(PageDirector.SIGN_UP, Router.Type.FORWARD));
            return responseContent;
        }

        SignUpService signUpService = new SignUpServiceImpl();

        try {
            request.setAttribute("successRegStart", signUpService.sendSignUpMessage(user));
        } catch (ServiceException e) {
            e.printStackTrace();
        }



        responseContent.setRouter(new Router(PageDirector.LOGIN, Router.Type.FORWARD));
        return responseContent;
    }
}
