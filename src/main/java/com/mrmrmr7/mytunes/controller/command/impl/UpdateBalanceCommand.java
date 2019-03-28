package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.builder.Builder;
import com.mrmrmr7.mytunes.builder.exception.BuilderException;
import com.mrmrmr7.mytunes.builder.impl.UserIdBuilder;
import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BalanceServiceImpl;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.validator.Validator;
import com.mrmrmr7.mytunes.validator.impl.PaymentValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

public class UpdateBalanceCommand implements Command {
    private final String PAYMENT_COUNT = "paymentCount";
    private final String COOKIES = "cookies";

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        BalanceService balanceService = new BalanceServiceImpl();

        Validator validator = new PaymentValidator();
        if(validator.validate(request)) {
            try {
                Builder userIdBuilder = new UserIdBuilder();
                int userId = (int) userIdBuilder.build(request);
                int paymentCount = Integer.valueOf(request.getParameter(PAYMENT_COUNT));
                boolean success = balanceService.updateBalance(userId, paymentCount);

                httpServletResponse.addCookie(new Cookie("success", String.valueOf(success)));
            } catch (BuilderException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA) + e.getMessage());
            }
        } else {
            httpServletResponse.addCookie(new Cookie("notValidData", String.valueOf(true)));
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.REDIRECT_PATH.getValue() + CommandDirector.VIEW_UPDATE_BALANCE_PAGE.getValue(), Router.Type.REDIRECT));
        return responseContent;
    }
}
