package com.mrmrmr7.mytunes.filter;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.impl.ServiceUserImpl;
import com.mrmrmr7.mytunes.util.PageDirector;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class SessionFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("Filter of [" + "/jsp/*, " + "crud] init successfully");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandDirector.COMMAND.getValue());
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServiceUserImpl serviceUser = new ServiceUserImpl();
        boolean isAuthorized = false;

        try {

            isAuthorized = serviceUser.isAuthorized(command, httpServletRequest);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (((HttpServletRequest) servletRequest).getRequestURI().contains("crud")) {
            if (isAuthorized) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletRequest.getSession(true);
                clearCookie(servletResponse);
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + PageDirector.LANDING.getValue());
            }
        } else {
            if (isAuthorized) {
                httpServletRequest.getRequestDispatcher(PageDirector.ACCOUNT.getValue()).forward(servletRequest, servletResponse);
            } else {
                httpServletRequest.getSession(true
                );
                clearCookie(servletResponse);
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

    @Override
    public void destroy() {
        System.out.println("Destroyed");
    }


}
