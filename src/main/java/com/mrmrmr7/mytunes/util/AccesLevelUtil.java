package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.controller.command.AccessLevel;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AccesLevelUtil {
    public static AccessLevel showAccessLevelFromReques(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);
        Integer role = claims.get("userRole", Integer.class);

        if (role == 1) {
            return AccessLevel.ADMIN;
        }

        if (role == 2) {
            return AccessLevel.USER;
        }

        return AccessLevel.ALL;
    }
}
