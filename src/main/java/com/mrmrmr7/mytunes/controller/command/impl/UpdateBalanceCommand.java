package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BalanceServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class UpdateBalanceCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.UPDATE_BALANCE.getValue() + " command detected");

        BalanceService balanceService = new BalanceServiceImpl();

        try {
            request.setAttribute("balanceType", "update");
            request.setAttribute("newBalance", balanceService.updateBalance(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.BALANCE_CURRENT, Router.Type.FORWARD));

        return responseContent;
    }
}
