package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.User;

import java.util.Optional;

public interface UserDAOExtended extends GenericDAO<User, Integer> {

    Optional<User> getByLogin(String login) throws DAOException;
}
