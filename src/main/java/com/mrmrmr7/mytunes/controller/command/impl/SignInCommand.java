package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.dto.UserDto;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.service.impl.UserDtoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.SignInValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SignInCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();

        String login = request.getParameter(RequestDirector.LOGIN.getValue());
        String password = request.getParameter(RequestDirector.PASSWORD.getValue());

        Validator validator = new SignInValidator();

        if (validator.validate(request)) {
            UserServiceImpl userService = new UserServiceImpl();
            boolean isSignIn = userService.isRightUser(login, password);
            if (isSignIn) {
                UserDtoServiceImpl userDtoServiceImpl = new UserDtoServiceImpl();
                UserDto userDto = userDtoServiceImpl.getDtoByLogin(login);

                request.setAttribute("role", userDto.getRole().getId());
                request.setAttribute("userDto", userDto);

                Map<String, Cookie> cookieMap = userService.getCookies(login);

                httpServletResponse.addCookie(cookieMap.get(RequestDirector.TOKEN.getValue()));
                httpServletResponse.addCookie(cookieMap.get(RequestDirector.PUBLIC_KEY.getValue()));

                responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_PROFILE_PAGE.getValue(), Router.Type.REDIRECT));
            } else {
                httpServletResponse.addCookie(new Cookie("failSignIn", String.valueOf(true)));
                responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), Router.Type.REDIRECT));
            }
        } else {
            httpServletResponse.addCookie(new Cookie("failData", String.valueOf(true)));
            responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), Router.Type.REDIRECT));
        }

        return responseContent;
    }
}
