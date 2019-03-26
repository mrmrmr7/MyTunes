package com.mrmrmr7.mytunes.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.AccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandAccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.AccesLevelUtil;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = "/redirect*", filterName = "redirectFilter")
public class RedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        AccessLevel accessLevel = null;

        boolean isAuthorized = false;


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserServiceImpl serviceUser = new UserServiceImpl();

        String command = httpServletRequest.getParameter("to");

        try {
            isAuthorized = serviceUser.isAuthorized(command, httpServletRequest);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        accessLevel = CommandAccessLevel.getInstance().showLevel(command);

        if (accessLevel == AccessLevel.ALL) {
            if (!isAuthorized) {
                httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + "/crud?command=" + command).forward(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/crud?command=" + CommandDirector.VIEW_PROFILE_PAGE.getValue());
            }
        } else {

            if (isAuthorized) {
                if (accessLevel == AccessLevel.USER) {
                    httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + "/crud?command=" + command).forward(servletRequest, servletResponse);
                } else {
                    if (AccesLevelUtil.showAccessLevelFromReques(servletRequest) == AccessLevel.ADMIN) {
                        httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + "/crud?command=" + command).forward(servletRequest, servletResponse);
                    } else {
                        httpServletRequest.getRequestDispatcher(((HttpServletRequest) servletRequest).getContextPath() + "/crud?command=" + CommandDirector.VIEW_PROFILE_PAGE.getValue()).forward(servletRequest, servletResponse);
                    }
                }
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getContextPath() + PageDirector.LANDING.getValue());
            }
        }
    }

    @Override
    public void destroy() {
    }
}