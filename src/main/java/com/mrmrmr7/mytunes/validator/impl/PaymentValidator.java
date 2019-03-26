package com.mrmrmr7.mytunes.validator.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.validator.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class PaymentValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest request) {
        String paymentCount = request.getParameter("paymentCount");
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();

        if (!cookieToken.isPresent()) {
            return false;
        }

        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());
        Integer userId = decodedJWT.getClaim("userId").asInt();

        if (userId == null) {
            return false;
        }

        if(paymentCount.length() > 10) {
            return false;
        }

        long count = Long.valueOf(paymentCount);
        return (count <= Integer.MAX_VALUE && count > 0);
    }
}
