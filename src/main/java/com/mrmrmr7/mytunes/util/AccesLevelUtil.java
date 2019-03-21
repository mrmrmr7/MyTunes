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
        String role = claims.get("userRole", String.class);

        if (role.equalsIgnoreCase("admin")) {
            return AccessLevel.ADMIN;
        }

        if (role.equalsIgnoreCase("user")) {
            return AccessLevel.USER;
        }

        return AccessLevel.ALL;
    }
}
