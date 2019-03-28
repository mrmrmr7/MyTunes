package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserMusicDtoService;
import com.mrmrmr7.mytunes.service.impl.UserMusicDtoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class ViewUserMusicCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserMusicDtoService userDtoServiceImpl = new UserMusicDtoServiceImpl();

        request.setAttribute("userMusicDto", userDtoServiceImpl.getUserMusicDto(request));

        Optional<Cookie> cookieSuccess = Arrays.stream(request.getCookies()).filter(s -> s.getName().equalsIgnoreCase("success")).findFirst();

        cookieSuccess.ifPresent(c -> {
            request.setAttribute("success", Boolean.valueOf(c.getValue()));
            c.setMaxAge(0);
            response.addCookie(c);
                }
        );

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_USER_MUSIC, Router.Type.FORWARD));
        return responseContent;
    }
}
