package com.mrmrmr7.mytunes.controller;

import com.mrmrmr7.mytunes.entity.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/*")
public class WebAppServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponce(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponce(req, resp);
    }

    public void doResponce(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("lolkek");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
