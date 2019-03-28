package com.mrmrmr7.mytunes.filter;

import com.mrmrmr7.mytunes.controller.command.AccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandAccessLevel;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ExceptionDescription;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.UserServiceImpl;
import com.mrmrmr7.mytunes.util.AccesLevelUtil;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.ExceptionHandler;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AccessLevelFilter")
public class AccessLevelFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("Filter of [" + "/jsp/*, " + "crud] init successfully");
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandDirector.COMMAND.getValue());

        AccessLevel accessLevel = null;

        if (command == null && (((HttpServletRequest) servletRequest).getRequestURI().equals("/") || ((HttpServletRequest) servletRequest).getRequestURI().equals("/index.jsp"))) {
            accessLevel = AccessLevel.ALL;
        } else if (command != null) {
            accessLevel = CommandAccessLevel.getInstance().showLevel(command);
        }

        boolean isAuthorized = false;

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserServiceImpl serviceUser = new UserServiceImpl();

        try {
            isAuthorized = serviceUser.isAuthorized(command, httpServletRequest);
        } catch (ServiceException e) {
            ExceptionDescription exceptionDescription = ExceptionHandler.getDescription(e.getMessage());
            httpServletRequest.setAttribute("errorCode", exceptionDescription.getErrorCode());
            httpServletRequest.setAttribute("errorMessage", exceptionDescription.getMessage());
            httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + PageDirector.ERROR_PAGE).forward(servletRequest, servletResponse);
        }

        if (accessLevel == AccessLevel.ALL) {
            if (!isAuthorized) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/crud?command=" + CommandDirector.VIEW_PROFILE_PAGE.getValue());
            }
        } else {

            if (isAuthorized) {
                if (accessLevel == AccessLevel.USER) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    if (AccesLevelUtil.showAccessLevelFromReques(servletRequest) == AccessLevel.ADMIN) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpServletRequest.getRequestDispatcher(((HttpServletRequest) servletRequest).getContextPath() + "/crud?command=" + PageDirector.LANDING.getValue()).forward(servletRequest, servletResponse);
                    }
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    private void clearCookie(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie cookieToken = new Cookie("token", "");
        cookieToken.setMaxAge(0);
        Cookie cookiePublicKey = new Cookie("publicKey", "");
        cookiePublicKey.setMaxAge(0);
        httpServletResponse.addCookie(cookiePublicKey);
        httpServletResponse.addCookie(cookieToken);
    }

    public void destroy() {
        System.out.println("Destroyed");
    }

}
