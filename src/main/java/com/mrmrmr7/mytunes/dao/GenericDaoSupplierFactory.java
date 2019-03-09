package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.impl.AuthorDao;
import com.mrmrmr7.mytunes.dao.impl.CompositionDao;
import com.mrmrmr7.mytunes.dao.impl.UserDao;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GenericDaoSupplierFactory {
    private Map<Class, Supplier<GenericDao>> daoMap = new HashMap<>();

    private GenericDaoSupplierFactory() {
        daoMap.put(User.class, UserDao::new);
        daoMap.put(Author.class, AuthorDao::new);
        daoMap.put(Composition.class, CompositionDao::new);
    }

    public GenericDao getDAO(Class entitiyClass) {
        return new AuthorDao();
    }
}
