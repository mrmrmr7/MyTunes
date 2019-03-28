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

public class ViewUpdateBalancePageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("success")).findFirst();
        Optional<Cookie> validDataCookie = Arrays.stream(request.getCookies()).filter(s -> s.getName().equals("notValidData")).findFirst();

        validDataCookie.ifPresent(c -> {
            request.setAttribute("notValidData", Boolean.valueOf(c.getValue()));
            c.setMaxAge(0);
            response.addCookie(c);
        });

        cookie.ifPresent(c -> {
            request.setAttribute("success", Boolean.valueOf(c.getValue()));
            c.setMaxAge(0);
            response.addCookie(c);
        });

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPDATE_BALANCE_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
