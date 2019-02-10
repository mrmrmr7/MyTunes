package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.DAOQuery;
import com.mrmrmr7.mytunes.entity.User;

import java.util.List;

public class ServiceQuery {
    private static ServiceQuery ourInstance = new ServiceQuery();

    public static ServiceQuery getInstance() {
        return ourInstance;
    }

    private ServiceQuery() {
    }

    public List<User> getByID(int id) throws ServiceException {
        if (id > 0) {
            DAOQuery daoQuery = DAOQuery.getInstance();
            try {
                return daoQuery.getByID(id);
            } catch (DAOException e) {
                throw new ServiceException("Could not find user by such id: " + id);
            }
        } else {
            throw new ServiceException("False user id");
        }
    }
}
