package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dto.UserDto;

public interface UserDtoService {

    UserDto getDtoById(int id) throws DaoException;
    UserDto getDtoByLogin(String login) throws DaoException;
}
