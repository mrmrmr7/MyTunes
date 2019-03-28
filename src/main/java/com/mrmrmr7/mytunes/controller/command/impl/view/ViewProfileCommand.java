package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserDtoServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProfileCommand implements Command {

    private static final String PATH_ACCOUNT = "account";

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserDtoServiceImpl userDtoServiceImpl = new UserDtoServiceImpl();

        userDtoServiceImpl.setDtoFromToken(request);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.ACCOUNT, Router.Type.FORWARD));
        return responseContent;
    }
}
