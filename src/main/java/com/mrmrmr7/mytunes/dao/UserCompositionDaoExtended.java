package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserComposition;

import java.util.List;
import java.util.Optional;

public interface UserCompositionDaoExtended {
    List<Integer> getCortageIdByCompositionId(Integer id) throws DaoException;

    Optional<UserComposition> getByCortagePK(Integer i) throws DaoException;
}
