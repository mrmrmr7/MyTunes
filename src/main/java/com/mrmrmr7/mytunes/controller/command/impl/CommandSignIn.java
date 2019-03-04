package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.service.ServiceSession;
import com.mrmrmr7.mytunes.service.ServiceSignIn;
import com.mrmrmr7.mytunes.service.impl.ServiceSessionImpl;
import com.mrmrmr7.mytunes.service.impl.ServiceSignInImpl;
import com.mrmrmr7.mytunes.service.impl.ServiceUserImpl;

import javax.servlet.http.HttpServletRequest;

public class CommandSignIn implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) throws Exception {
        System.out.println("Command sing in detected");
        request.removeAttribute("command");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean isSignIn = false;

        if (login != null) {
            if (password != null) {
                ServiceSignIn serviceSignIn = new ServiceSignInImpl();
                isSignIn = serviceSignIn.execute(login, password);
            }
        }

        ResponseContent responseContent = new ResponseContent();
        System.out.println("Is log-in: " + isSignIn);

        if (isSignIn) {
            ServiceSession serviceSession = new ServiceSessionImpl();
            String session_hash = serviceSession.execute(login);
            ServiceUserImpl serviceUser = new ServiceUserImpl();
            System.out.println("here");
            int user_id = serviceUser.getIdByLogin(login);
            SessionData sessionData = new SessionData(user_id,session_hash);
            request.getSession().setAttribute("session_data", sessionData   );
            System.out.println(serviceUser.getIdByLogin(login));
            responseContent.setRouter(new Router("/jsp/account.jsp", "forward"));
        } else {
            responseContent.setRouter(new Router("/index.jsp", "redirect"));
        }

        return responseContent;
    }
}
