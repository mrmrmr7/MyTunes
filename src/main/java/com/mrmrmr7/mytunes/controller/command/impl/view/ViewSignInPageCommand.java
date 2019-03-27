package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class ViewSignInPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.VIEW_SIGN_IN_PAGE.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();
        Optional<Cookie> failSignIn = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("failSignIn")).findFirst();
        Optional<Cookie> failData = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("failData")).findFirst();
        Optional<Cookie> resetStart = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("resetStart")).findFirst();
        Optional<Cookie> changePassword = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("changePassword")).findFirst();

        failSignIn.ifPresent(s -> {
            request.setAttribute("failSignIn", Boolean.valueOf(s.getValue()));
            s.setMaxAge(0);
            response.addCookie(s);
        });

        failData.ifPresent(s -> {
            request.setAttribute("failData", Boolean.valueOf(s.getValue()));
            s.setMaxAge(0);
            response.addCookie(s);
        });

        resetStart.ifPresent(s -> {
            request.setAttribute("resetStart", Boolean.valueOf(s.getValue()));
            s.setMaxAge(0);
            response.addCookie(s);
        });

        changePassword.ifPresent(s -> {
            request.setAttribute("changePassword", Boolean.valueOf(s.getValue()));
            s.setMaxAge(0);
            response.addCookie(s);
        });


        responseContent.setRouter(new Router(PageDirector.SIGN_IN, Router.Type.FORWARD));
        return responseContent;
    }
}
