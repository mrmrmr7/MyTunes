package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserBonusService;
import com.mrmrmr7.mytunes.service.impl.UserBonusServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ViewAdminAddUserBonus implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        int bonusId = Integer.valueOf(request.getParameter("bonusId"));
        String userLogin = request.getParameter("userLogin");
        UserBonusService userBonusService = new UserBonusServiceImpl();

        try {
            request.setAttribute("success", userBonusService.addBonusToUser(userLogin, bonusId));
        } catch (ServiceException e) {
            System.out.println("ups, upalo");
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.ADMIN_ADD_USER_BONUS, Router.Type.FORWARD));
        return null;
    }
}
