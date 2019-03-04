package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;

public interface ServiceSignUp {
    boolean execute(User user) throws ServiceException;
}
