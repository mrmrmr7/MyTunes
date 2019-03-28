package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.UserService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.FinishRestartPasswordValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinishRestartPasswordCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        Validator validator = new FinishRestartPasswordValidator();
        if (validator.validate(request)) {
            UserService userServiceImpl = new UserServiceImpl();
            int id = Integer.valueOf(request.getParameter("userId"));
            String password = request.getParameter("password");
            userServiceImpl.changePassword(id, password);
            httpServletResponse.addCookie(new Cookie("changePassword", String.valueOf(true)));
        }
        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), Router.Type.REDIRECT));
        return responseContent;
    }
}
