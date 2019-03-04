package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceUser;

public class ServiceUserImpl implements ServiceUser {
    @Override
    public int getIdByLogin(String login) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        int id = 0;

        try {
            id = userDAO.getByLogin(login).get().getId();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return id;
    }
}
