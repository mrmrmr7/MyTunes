package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.User;

import java.util.Optional;

public interface UserDaoExtended extends GenericDao<User, Integer> {

    @AutoConnection
    Optional<User> getByLogin(String login) throws DaoException;

    @AutoConnection
    Optional<User> getByEmail(String email) throws DaoException;
}
