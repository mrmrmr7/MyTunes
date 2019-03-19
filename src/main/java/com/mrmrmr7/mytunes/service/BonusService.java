package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import java.util.List;

public interface BonusService {
    List<Bonus> getAll() throws ServiceException;
}
