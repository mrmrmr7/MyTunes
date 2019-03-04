package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceSignIn;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class ServiceSignInImpl implements ServiceSignIn {

    @Override
    public boolean execute(final String login, final String password) throws ServiceException {
        System.out.println("Service sing in found");
        UserDAO userDAO = new UserDAO();
        Optional<User> user;

        try {
            user = userDAO.getByLogin(login);
            System.out.println(user.toString());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        boolean isRightPassword = false;

        if (user.isPresent()) {
            isRightPassword = BCrypt.checkpw(password, user.get().getPassword());
        }

        return isRightPassword;
    }
}
