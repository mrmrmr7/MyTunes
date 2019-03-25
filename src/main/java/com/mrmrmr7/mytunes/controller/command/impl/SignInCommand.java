package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.controller.command.exception.CommandException;
import com.mrmrmr7.mytunes.dto.UserDto;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.UserDtoService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.service.impl.UserDtoServiceImpl;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.SignInValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignInCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws CommandException {
        System.out.println(CommandDirector.SIGN_IN.getValue() + " command in detected");
        ResponseContent responseContent = new ResponseContent();

        try {
            String login = request.getParameter(RequestDirector.LOGIN.getValue());
            String password = request.getParameter(RequestDirector.PASSWORD.getValue());

            Validator validator = new SignInValidator();

            Map<String , String> map = new HashMap<>();
            map.put(RequestDirector.LOGIN.getValue(), login);
            map.put(RequestDirector.PASSWORD.getValue(), password);

            if (validator.validate(map)) {
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

                    responseContent.setRouter(new Router(PageDirector.ACCOUNT, Router.Type.FORWARD));
                } else {
                    request.setAttribute("failSignIn", true);
                    responseContent.setRouter(new Router(PageDirector.SIGN_IN, Router.Type.FORWARD));
                }
            } else {
                request.setAttribute("failData", true);
                responseContent.setRouter(new Router(PageDirector.SIGN_IN, Router.Type.FORWARD));
            }

        } catch (ServiceException e) {
            throw new CommandException(e.getMessage() + ":" + ExceptionDirector.CMD_SIN_PRC.getValue());
        }

        return responseContent;
    }

    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.LANDING, Router.Type.FORWARD));
        return responseContent;
    }
}
