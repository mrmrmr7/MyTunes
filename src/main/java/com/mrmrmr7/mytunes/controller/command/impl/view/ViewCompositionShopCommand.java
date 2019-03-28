package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.CompositionServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class ViewCompositionShopCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CompositionService compositionDtoService = new CompositionServiceImpl();

        request.setAttribute("compositionDtoList", compositionDtoService.getAllNotUserCompositionDto(request));
        Optional<Cookie> cookieSuccess = Arrays.stream(request.getCookies()).filter(s -> s.getName().equalsIgnoreCase("success")).findFirst();
        Optional<Cookie> cookieName = Arrays.stream(request.getCookies()).filter(s -> s.getName().equalsIgnoreCase("compositionName")).findFirst();

        cookieSuccess.ifPresent(c -> {
            request.setAttribute("success", Boolean.valueOf(c.getValue()));
            c.setMaxAge(0);
            response.addCookie(c);
        });

        cookieName.ifPresent(c -> {
            request.setAttribute("compositionName", c.getValue());
            c.setMaxAge(0);
            response.addCookie(c);
        });

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.COMPOSITION_SHOP, Router.Type.FORWARD));
        return responseContent;
    }
}
