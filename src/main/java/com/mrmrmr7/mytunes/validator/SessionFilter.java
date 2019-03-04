package com.mrmrmr7.mytunes.validator;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceSession;
import com.mrmrmr7.mytunes.service.impl.ServiceSessionImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*","/crud"})
public class SessionFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("Filter of [" + "/jsp/*, "  +"crud] init successfully");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandDirector.COMMAND.getValue());
        if (command == null || !command.equals("signIn")) {

            final HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession(false);
            if(httpSession != null) {

                SessionData sessionData = (SessionData) httpSession.getAttribute("session_data");
                if(sessionData != null) {

                    try {
                        ServiceSession serviceSession = new ServiceSessionImpl();
                        if (!serviceSession.check(sessionData)) {
                            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PageDirector.LANDING_PAGE);
                        }
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                } else {
                    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PageDirector.LANDING_PAGE);
                }

            } else {
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PageDirector.LANDING_PAGE);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed");
    }
}
