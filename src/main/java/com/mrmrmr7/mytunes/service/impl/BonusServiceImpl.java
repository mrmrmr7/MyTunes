package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.service.BonusService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

public class BonusServiceImpl implements BonusService {

    @Override
    public List<Bonus> getAll() throws ServiceException {
        List<Bonus> bonusList;

        try {
            bonusList = JdbcDaoFactory.getInstance().getDao(Bonus.class).getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return bonusList;
    }
}
