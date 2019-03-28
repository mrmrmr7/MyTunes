package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.SignUpService;
import com.mrmrmr7.mytunes.service.impl.SignUpServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpFinishCommand implements Command {
    private final static String PARAMETER_TOKEN = "token";

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        SignUpService signUpService = new SignUpServiceImpl();

        request.setAttribute("successRegFinish", signUpService.finishSignUp(request));

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.LOGIN, Router.Type.FORWARD));

        return responseContent;
    }
}
