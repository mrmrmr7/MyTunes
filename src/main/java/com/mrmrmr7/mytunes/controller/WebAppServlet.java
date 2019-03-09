package com.mrmrmr7.mytunes.controller;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.CommandExtended;
import com.mrmrmr7.mytunes.controller.command.CommandProvider;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.exception.PersistException;
import com.mrmrmr7.mytunes.entity.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "webapp", urlPatterns = "/crud")
public class WebAppServlet extends HttpServlet {

    private static final String ATTRIBUTE_VIEW_NAME = "viewName";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!response.isCommitted()) {
            String commandName = request.getParameter(CommandDirector.COMMAND.getValue());

            Command command = CommandProvider.getInstance().takeCommand(commandName);
            ResponseContent responseContent;

            try {
                if (commandName.equals(CommandDirector.SIGN_IN.getValue()) ||
                    commandName.equals(CommandDirector.LOG_OUT.getValue())) {
                    responseContent = command.process(request, response);
                } else {
                    responseContent = command.process(request);
                }

                if (responseContent.getRouter().getType().equals(Router.Type.REDIRECT.getValue())) {
                    System.out.println(request.getAttribute(ATTRIBUTE_VIEW_NAME));
                    response.sendRedirect(request.getContextPath() + responseContent.getRouter().getRoute());
                } else {
                    System.out.println("Forward to " + request.getContextPath() + responseContent.getRouter().getRoute());
                    request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
                }
            } catch (ServletException e) {
                System.out.println("Trouble");
            }
        }
    }
}