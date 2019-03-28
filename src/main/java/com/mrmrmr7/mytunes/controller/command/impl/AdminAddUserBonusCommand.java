package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserBonusService;
import com.mrmrmr7.mytunes.service.impl.UserBonusServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserBonusCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        int bonusId = Integer.valueOf(request.getParameter("bonusId"));
        String userLogin = request.getParameter("userLogin");
        UserBonusService userBonusService = new UserBonusServiceImpl();

        httpServletResponse.addCookie(new Cookie("success", String.valueOf(userBonusService.addBonusToUser(userLogin, bonusId))));

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.ADMIN_VIEW_ADD_USER_BONUS_PAGE.getValue(), Router.Type.FORWARD));
        return responseContent;
    }
}
