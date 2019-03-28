package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.entity.UserBonus;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.UserBonusService;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

public class UserBonusServiceImpl implements UserBonusService {
    @Override
    public boolean addBonusToUser(String userName, int bonusId) throws ServiceException {

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<User, Integer> userDao = JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            GenericDao<UserBonus, Integer> userBonusDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserBonus.class);

            transactionManager.begin(userDao, userBonusDao);

            Optional<User> userOptional = ((UserDaoExtended)userDao).getByLogin(userName);

            if (userOptional.isPresent()) {
                int userId = userOptional.get().getId();
                Optional<UserBonus> userBonusOptional = userBonusDao.getByPK(userId);

                if(userBonusOptional.isPresent()) {
                    List<Integer> userBonusList = userBonusOptional.get().getBonusIdList();
                    if (!userBonusList.contains(bonusId)) {
                        UserBonus userBonus = new UserBonus(userId, bonusId);
                        userBonusDao.insert(userBonus);
                    } else {
                        return false;
                    }
                } else {
                    UserBonus userBonus = new UserBonus(userId, bonusId);
                    userBonusDao.insert(userBonus);
                }
            } else {
                return false;
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return true;
    }
}
