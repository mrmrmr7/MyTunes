package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.impl.AuthorDAO;
import com.mrmrmr7.mytunes.dao.impl.CompositionDAO;
import com.mrmrmr7.mytunes.dao.impl.UserDAO;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DAOFactory {
    private Map<Class, Supplier<GenericDAO>> daoMap = new HashMap<>();

    private DAOFactory() {
        daoMap.put(User.class, UserDAO::new);
        daoMap.put(Author.class, AuthorDAO::new);
        daoMap.put(Composition.class, CompositionDAO::new);
    }

    public GenericDAO getDAO(Class entitiyClass) {
        return new AuthorDAO();
    }
}
