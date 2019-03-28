package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.CompositionServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.BuyCompositionValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCompositionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CompositionService compositionDtoService = new CompositionServiceImpl();

        Validator validator = new BuyCompositionValidator();

        request.removeAttribute("compositionDtoList");

        if (validator.validate(request)) {
            response.addCookie(new Cookie("success", String.valueOf(true)));
            response.addCookie(new Cookie("compositionName", request.getParameter("compositionName")));
        }

        request.setAttribute("compositionDtoList", compositionDtoService.getAllNotUserCompositionDto(request));

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_COMPOSITION_SHOP.getValue(), Router.Type.REDIRECT));

        return responseContent;
    }
}
