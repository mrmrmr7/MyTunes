package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserComposition;
import com.mrmrmr7.mytunes.service.UserCompositionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserCompositionServiceImpl implements UserCompositionService {

    public List<Composition> showUserComposition(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());
        Optional<User> user;

        try {
            user = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(decodedJWT.getClaim("userId").asInt());
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
        }

        if (!user.isPresent()) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA));
        }

        Optional<UserComposition> userCompositionOptional;
        List<Composition> compositionList = new ArrayList<>();

        try {
            userCompositionOptional = JdbcDaoFactory.getInstance().getDao(UserComposition.class).getByPK(user.get().getId());
            if (userCompositionOptional.isPresent()) {
                UserComposition u = userCompositionOptional.get();
                for (int c : u.getCortageIdList()) {
                    try {
                        Optional<Composition> compositionOptional = JdbcDaoFactory.getInstance().getDao(Composition.class).getByPK(c);
                        compositionOptional.ifPresent(compositionList::add);
                    } catch (DaoException e) {
                        throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
        }

        return compositionList;
    }
}
