package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.service.GenreService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    @Override
    public List<Genre> getGenreList() throws ServiceException {
        List<Genre> genreList;

        try {
            genreList = JdbcDaoFactory.getInstance().getDao(Genre.class).getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return genreList;
    }
}
