package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.BalanceService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class BalanceServiceImpl implements BalanceService {


    @Override
    public long updateBalance(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);
        Optional<User> user;

        try {
            user = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(claims.get("userId", Integer.class));

            if (!user.isPresent()) {
                throw new ServiceException("No such user");
            }

            long  paymentCount = Long.valueOf(request.getParameter("paymentCount"));

            user.get().setBalance(user.get().getBalance() + paymentCount);
            JdbcDaoFactory.getInstance().getDao(User.class).update(user.get());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return user.get().getBalance();

    }

    @Override
    public String getBalanceById(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);
        Optional<User> user;

        try {
            user = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(claims.get("userId", Integer.class));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        if (!user.isPresent()) {
            throw new ServiceException("No such user");
        }

        return String.valueOf(user.get().getBalance());
    }
}
