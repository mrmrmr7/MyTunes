package com.mrmrmr7.mytunes.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.AccessLevel;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class AccesLevelUtil {
    public static AccessLevel showAccessLevelFromReques(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        Integer role = decodedJWT.getClaim("userRole").asInt();

        if (role == 1) {
            return AccessLevel.ADMIN;
        }

        if (role == 2) {
            return AccessLevel.USER;
        }

        return AccessLevel.ALL;
    }
}
