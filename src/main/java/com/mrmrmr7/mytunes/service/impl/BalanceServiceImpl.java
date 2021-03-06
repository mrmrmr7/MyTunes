package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

public class BalanceServiceImpl implements BalanceService {


    @Override
    public boolean updateBalance(int userId, int paymentCount) throws ServiceException {
        Optional<User> user;
        try {
            user = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(userId);
            if (!user.isPresent()) {
                throw new ServiceException("No such user");
            }
            user.get().setBalance(user.get().getBalance() + paymentCount);
            JdbcDaoFactory.getInstance().getDao(User.class).update(user.get());
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DAO) + e.getMessage());
        }

        return true;

    }

    @Override
    public String getBalanceById(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        Optional<User> user;
        try {
            user = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(decodedJWT.getClaim("userId").asInt());
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DAO) + e.getMessage());
        }

        if (!user.isPresent()) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
        }

        return String.valueOf(user.get().getBalance());
    }
}
