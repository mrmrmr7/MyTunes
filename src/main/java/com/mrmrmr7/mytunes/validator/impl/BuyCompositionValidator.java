package com.mrmrmr7.mytunes.validator.impl;

import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.validator.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class BuyCompositionValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        if (!cookieToken.isPresent() || !cookiePublicKey.isPresent()) {
            return false;
        }

        String token = cookieToken.get().getValue();
        String publicKey = cookiePublicKey.get().getValue();
        String compositionName = request.getParameter("compositionName");

        if (compositionName == null) {
            return false;
        }

        UserServiceImpl userService = new UserServiceImpl();

        try {
            return userService.validateTokenById(token, publicKey);
        } catch (ServiceException e) {
            return false;
        }
    }
}
