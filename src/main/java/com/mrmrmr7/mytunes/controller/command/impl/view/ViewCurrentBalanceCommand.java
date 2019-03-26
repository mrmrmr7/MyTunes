package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BalanceServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCurrentBalanceCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.VIEW_CURRENT_BALANCE_PAGE.getValue() + " command detected");

        BalanceService balanceService = new BalanceServiceImpl();

        try {
            request.setAttribute("balance", balanceService.getBalanceById(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.VIEW_CURRENT_BALANCE_PAGE, Router.Type.FORWARD));

        return responseContent;
    }
}
