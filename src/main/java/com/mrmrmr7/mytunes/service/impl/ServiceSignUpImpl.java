package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceSignUp;

public class ServiceSignUpImpl implements ServiceSignUp {

    @Override
    public boolean execute(User user) throws ServiceException {
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.insert(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }
}
