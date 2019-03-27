package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.RestartPasswordValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class ViewRestartPasswordPage implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        System.out.println(CommandDirector.VIEW_RESTART_PASSWORD_PAGE.getValue() + " command detected");

        Validator validator = new RestartPasswordValidator();

        if (validator.validate(request)) {
            String token = request.getParameter(RequestDirector.TOKEN.getValue());
            int userId = JWT.decode(token).getClaim("userId").asInt();
            httpServletResponse.addCookie(new Cookie("userId", String.valueOf(userId)));
            request.setAttribute("userId", userId);
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_RESET_PASSWORD_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
