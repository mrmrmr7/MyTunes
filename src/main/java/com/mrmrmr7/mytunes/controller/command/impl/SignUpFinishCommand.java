package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.SignUpService;
import com.mrmrmr7.mytunes.service.impl.SignUpServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class SignUpFinishCommand implements Command {
    private final static String PARAMETER_TOKEN = "token";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println("SignUp command detected");

        SignUpService signUpService = new SignUpServiceImpl();

        try {
            request.setAttribute("succesRegFinish", signUpService.finishSignUp(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.LOGIN, Router.Type.FORWARD));

        return responseContent;
    }
}
