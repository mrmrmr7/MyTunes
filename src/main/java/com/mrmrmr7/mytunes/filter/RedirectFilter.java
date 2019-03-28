package com.mrmrmr7.mytunes.filter;

import com.mrmrmr7.mytunes.controller.command.AccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandAccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.AccesLevelUtil;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/redirect*", filterName = "redirectFilter")
public class RedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserServiceImpl serviceUser = new UserServiceImpl();

        String command = httpServletRequest.getParameter("to");

        boolean isAuthorized = false;
        try {
            isAuthorized = serviceUser.isAuthorized(command, httpServletRequest);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        AccessLevel accessLevel = CommandAccessLevel.getInstance().showLevel(command);

        if (accessLevel == AccessLevel.ALL) {
            if (!isAuthorized) {
                httpServletRequest.getRequestDispatcher("/crud?command=" + command).forward(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/crud?command=" + CommandDirector.VIEW_PROFILE_PAGE.getValue());
            }
        } else {

            if (isAuthorized) {
                if (accessLevel == AccessLevel.USER) {
                    httpServletRequest.getRequestDispatcher("/crud?command=" + command).forward(servletRequest, servletResponse);
                } else {
                    if (AccesLevelUtil.showAccessLevelFromReques(servletRequest) == AccessLevel.ADMIN) {
                        httpServletRequest.getRequestDispatcher("/crud?command=" + command).forward(servletRequest, servletResponse);
                    } else {
                        httpServletRequest.getRequestDispatcher("/crud?command=" + CommandDirector.VIEW_PROFILE_PAGE.getValue()).forward(servletRequest, servletResponse);
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