package com.mrmrmr7.mytunes.controller;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandProvider;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.exception.PersistException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "webapp", urlPatterns = "/crud")
public class WebAppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Command command = CommandProvider.getInstance().takeCommand(request.getParameter("command"));
        ResponseContent responseContent;
        try {
            responseContent = command.process(request);
            if (responseContent.getRouter().getType().equals("redirect")) {
                System.out.println(request.getAttribute("viewName"));
                response.sendRedirect(request.getContextPath() + responseContent.getRouter().getRoute());
            } else {
                System.out.println("Forward to " + request.getContextPath() + responseContent.getRouter().getRoute());
                request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
            response.sendRedirect("/jsp/error.jsp");
            //тут должно выводиться сообщение
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.sendRedirect("/jsp/error.jsp");
        }
    }
}