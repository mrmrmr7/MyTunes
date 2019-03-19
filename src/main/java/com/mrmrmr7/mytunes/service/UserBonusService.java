package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.service.exception.ServiceException;

public interface UserBonusService {
    boolean addBonusToUser(String userName, int bonusId) throws ServiceException;
}
