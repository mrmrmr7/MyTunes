package com.mrmrmr7.mytunes.controller.command.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.builder.Builder;
import com.mrmrmr7.mytunes.builder.exception.BuilderException;
import com.mrmrmr7.mytunes.builder.impl.UserIdBuilder;
import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.exception.CommandException;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BalanceServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.PaymentValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateBalanceCommand implements Command {
    private final String PAYMENT_COUNT = "paymentCount";
    private final String COOKIES = "cookies";

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws CommandException {
        System.out.println(CommandDirector.UPDATE_BALANCE.getValue() + " command detected");

        BalanceService balanceService = new BalanceServiceImpl();

        Validator validator = new PaymentValidator();
        boolean success = false;
        if(validator.validate(request)) {
            try {
                Builder userIdBuilder = new UserIdBuilder();
                int userId = (int) userIdBuilder.build(request);
                int paymentCount = Integer.valueOf(request.getParameter(PAYMENT_COUNT));
                success =  balanceService.updateBalance(userId, paymentCount);
            } catch (ServiceException e) {
                throw new CommandException(e.getMessage() + ":" + CommandDirector.UPDATE_BALANCE.getCode());
            } catch (BuilderException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("notValidCount", true);
        }

        ResponseContent responseContent = new ResponseContent();

        if (success) {
            Cookie cookie = new Cookie("success", "true");
            responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_UPDATE_BALANCE_PAGE.getValue(), Router.Type.REDIRECT));
        }

        return responseContent;
    }
}
