package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dto.UserDto;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

public interface UserDtoService {

    UserDto getDtoById(int id) throws DaoException, ServiceException;
    UserDto getDtoByLogin(String login) throws DaoException, ServiceException;
}
