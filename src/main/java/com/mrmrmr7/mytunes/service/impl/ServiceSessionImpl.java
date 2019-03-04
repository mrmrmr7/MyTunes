package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.SessionDataDAO;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceSession;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Calendar;
import java.util.Optional;

public class ServiceSessionImpl implements ServiceSession {

    @Override
    public String execute(String login) throws ServiceException {
        System.out.println("Service session detected");
        SessionDataDAO sessionDataDAO = new SessionDataDAO();
        UserDAO userDAO = new UserDAO();

        Optional<User> user;

        try {
            user = userDAO.getByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        System.out.println(user.toString());


        String reverseLogin = new StringBuilder(login)
                .reverse()
                .toString();

        String session_hash = BCrypt.hashpw(reverseLogin.concat(String.valueOf(Calendar.getInstance().getTime().getTime())), BCrypt.gensalt());
        SessionData sessionData = new SessionData(user.get().getId(),session_hash);

        System.out.println("9 + " + sessionData.toString());

        try {
            System.out.println("Insert will be now");
            sessionDataDAO.delete(sessionData.getId());
            sessionDataDAO.insert(sessionData);
            System.out.println("Insert success");
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return session_hash;
    }

    @Override
    public boolean check(SessionData sessionData) throws ServiceException {
        SessionDataDAO sessionDataDAO = new SessionDataDAO();
        Optional<SessionData> sessionData1 = null;

        try {
            if (sessionData.getId() != 0) {
                sessionData1 = sessionDataDAO.getByPK(sessionData.getId());
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return sessionData.equals(sessionData1.get());
    }
}
