package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    @Override
    public List<Author> getAuthorList() throws ServiceException {
        List<Author> authorList = new ArrayList<>();
        try {
            authorList = JdbcDaoFactory.getInstance().getDao(Author.class).getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return authorList;
    }
}
