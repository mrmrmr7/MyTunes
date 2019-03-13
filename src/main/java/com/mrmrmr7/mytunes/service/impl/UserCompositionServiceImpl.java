package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserComposition;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.UserCompositionService;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCompositionServiceImpl implements UserCompositionService {

    public List<Composition> showUserComposition(HttpServletRequest request) throws ServiceException {
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

        Optional<UserComposition> userCompositionOptional;
        List<Composition> compositionList = new ArrayList<>();

        try {
            userCompositionOptional = JdbcDaoFactory.getInstance().getDao(UserComposition.class).getByPK(user.get().getId());
            userCompositionOptional.ifPresent(
                    u -> {
                        u.getCompositionIdList().forEach(
                                c -> {
                                    try {
                                        Optional<Composition> compositionOptional = JdbcDaoFactory.getInstance().getDao(Composition.class).getByPK(c);
                                        compositionOptional.ifPresent(
                                                compositionList::add
                                        );
                                    } catch (DaoException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
                    }
            );
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return compositionList;
    }
}
