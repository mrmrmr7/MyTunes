package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.SessionDataDAO;
import com.mrmrmr7.mytunes.dao.impl.TransactionManager;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.validator.ValidatorSignIn;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Optional;

public class ServiceUserImpl {
    public static final String ATTRIBUTE_SESSION_DATA = "session_data";

    public boolean login(String login, String password, HttpSession session) throws ServiceException {
        ValidatorSignIn validatorSignIn = new ValidatorSignIn();

        if (!validatorSignIn.isValid(login, password)) {
            return false;
        }

        UserDAO userDAO = new UserDAO();
        SessionDataDAO sessionDataDAO = new SessionDataDAO();

        TransactionManager transactionManager = new TransactionManager();
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

            session.setAttribute(ATTRIBUTE_SESSION_DATA, sessionData);

            transactionManager.commit();
            transactionManager.end();
        } catch (DAOException e) {
            transactionManager.rollBack();
            throw new ServiceException(e.getMessage());
        } finally {
            transactionManager.end();
        }

        return true;
    }

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

    public boolean isAuthorized (String command, HttpSession session) throws ServiceException {

        if (session == null) {
            return false;
        }

        if (command != null && command.equals(CommandDirector.SIGN_IN.getValue())) {
            return true;
        }

        SessionDataDAO sessionDataDAO = new SessionDataDAO();
        Optional<SessionData> sessionDataOptional;
        SessionData sessionData = (SessionData) session.getAttribute("session_data");

        if (sessionData == null) {
            return false;
        }

        try {
            sessionDataDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
            sessionDataOptional = sessionDataDAO.getByPK(sessionData.getId());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            sessionDataDAO.closeConnection();
        }

        return sessionDataOptional.filter(sessionData::equals).isPresent();
    }
}
