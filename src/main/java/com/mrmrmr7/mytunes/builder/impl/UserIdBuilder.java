package com.mrmrmr7.mytunes.builder.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.builder.Builder;
import com.mrmrmr7.mytunes.builder.exception.BuilderException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class UserIdBuilder implements Builder<Integer> {
    @Override
    public Integer build(HttpServletRequest request) throws BuilderException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());
        int id = decodedJWT.getClaim("userId").asInt();
        return id;
    }
}
