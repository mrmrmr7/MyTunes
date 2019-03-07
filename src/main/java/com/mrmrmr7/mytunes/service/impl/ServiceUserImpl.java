package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.SessionDataDAO;
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.validator.ValidatorSignIn;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

public class ServiceUserImpl implements ServiceUser {
    public static final String ATTRIBUTE_SESSION_DATA = "session_data";
    private static final String COOKIE_UUID = "uuid";
    private static final String COOKIE_UID = "uid";

    @Override
    public boolean login(String login, String password, HttpServletResponse response) throws ServiceException {
        ValidatorSignIn validatorSignIn = new ValidatorSignIn();

        if (!validatorSignIn.isValid(login, password)) {
            return false;
        }

        UserDAO userDAO = new UserDAO();
        SessionDataDAO sessionDataDAO = new SessionDataDAO();

        TransactionManagerImpl transactionManager = new TransactionManagerImpl();
        try {
            transactionManager.begin(userDAO, sessionDataDAO);

            Optional<User> user;
            user = userDAO.getByLogin(login);

            if (!user.isPresent()) {
                return false;
            }

            boolean isRightPassword = BCrypt.checkpw(password, user.get().getPassword());

            if (!isRightPassword) {
                return false;
            }

            String session_hash = BCrypt.hashpw(
                    (new StringBuilder(login)
                            .reverse()
                            .toString())
                            .concat(String.valueOf(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()
                                    )), BCrypt.gensalt());

            SessionData sessionData = new SessionData(user.get().getId(),session_hash);

            sessionDataDAO.delete(sessionData.getId());
            sessionDataDAO.insert(sessionData);

            Cookie cookieUUID = new Cookie(COOKIE_UUID, session_hash);
            Cookie cookieUID = new Cookie(COOKIE_UID, String.valueOf(user.get().getId()));
            response.addCookie(cookieUID);
            response.addCookie(cookieUUID);

            transactionManager.commit();
        } catch (DAOException e) {
            transactionManager.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            transactionManager.end();
        }

        return true;
    }

    @Override
    public void logout(HttpSession session) throws ServiceException {
        if (session != null) {
            SessionDataDAO sessionDataDAO = new SessionDataDAO();
            try {
                sessionDataDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
                SessionData sessionData = (SessionData) session.getAttribute(ATTRIBUTE_SESSION_DATA);

                if(sessionData != null) {
                    try {
                        sessionDataDAO.delete(sessionData.getId());
                        session.removeAttribute(ATTRIBUTE_SESSION_DATA);
                    } catch (DAOException e) {
                        e.printStackTrace();
                    } finally {
                        sessionDataDAO.closeConnection();
                    }
                }
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            } finally {
                sessionDataDAO.closeConnection();
            }
        }
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException {

        if (httpServletRequest == null) {
            return false;
        }

        if (command != null && command.equals(CommandDirector.SIGN_IN.getValue())) {
            return true;
        }

        SessionDataDAO sessionDataDAO = new SessionDataDAO();
        Optional<SessionData> sessionDataOptional;

        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<Cookie> cookieUUID = Arrays.stream(cookies).filter(s -> s.getName().equals("uuid")).findFirst();
        Optional<Cookie> cookieUID = Arrays.stream(cookies).filter(s -> s.getName().equals("uid")).findFirst();


        if (!cookieUUID.isPresent()) {
            return false;
        }

        if(!cookieUID.isPresent()) {
            return false;
        }



//        SessionData sessionData = (SessionData) session.getAttribute("session_data");
//
//        if (sessionData == null) {
//            return false;
//        }

        try {
            sessionDataDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
            sessionDataOptional = sessionDataDAO.getByPK(Integer.valueOf(cookieUID.get().getValue()));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            sessionDataDAO.closeConnection();
        }

        return sessionDataOptional.get().getSession_hash().equals(cookieUUID.get().getValue());
    }

}
