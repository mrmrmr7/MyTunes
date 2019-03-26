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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCompositionCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.BUY_COMPOSITION.getValue() + " command detected");

        MusicService musicService = new MusicServiceImpl();
        CompositionService compositionDtoService = new CompositionServiceImpl();

        try {
            request.removeAttribute("compositionDtoList");
            request.setAttribute("success", musicService.buyComposition(request));
            request.setAttribute("compositionDtoList", compositionDtoService.getAllNotUserCompositionDto(request));
            request.setAttribute("compositionName", request.getParameter("compositionName"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + PageDirector.COMPOSITION_SHOP.getValue(), Router.Type.REDIRECT));

        return responseContent;
    }
}
