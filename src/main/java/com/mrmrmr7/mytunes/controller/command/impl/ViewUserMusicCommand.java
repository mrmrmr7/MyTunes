package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.UserMusicDtoService;
import com.mrmrmr7.mytunes.service.impl.UserDtoServiceImpl;
import com.mrmrmr7.mytunes.service.impl.UserMusicDtoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class ViewUserMusicCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.VIEW_USER_MUSIC.getValue() + " command detected");

        UserMusicDtoService userDtoServiceImpl = new UserMusicDtoServiceImpl();

        try {
            request.setAttribute("userMusicDto", userDtoServiceImpl.getUserMusicDto(request));
        } catch (ServiceException e) {
            System.out.println("fail to get dto");
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_USER_MUSIC, Router.Type.FORWARD));
        return responseContent;
    }
}
