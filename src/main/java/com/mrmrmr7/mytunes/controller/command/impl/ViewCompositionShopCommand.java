package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.CompositionDtoService;
import com.mrmrmr7.mytunes.service.MusicService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BalanceServiceImpl;
import com.mrmrmr7.mytunes.service.impl.CompositionDtoServiceImpl;
import com.mrmrmr7.mytunes.service.impl.MusicServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewCompositionShopCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_COMPOSITION_SHOP.getValue() + " command detected");
        CompositionDtoService compositionDtoService = new CompositionDtoServiceImpl();
        try {
            request.setAttribute("compositionDtoList", compositionDtoService.getAllNotUserCompositionDto(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.COMPOSITION_SHOP, Router.Type.FORWARD));
        return responseContent;
    }
}
