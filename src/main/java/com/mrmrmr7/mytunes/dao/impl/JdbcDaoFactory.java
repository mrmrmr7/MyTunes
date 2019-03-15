package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static JdbcDaoFactory INSTANCE;
    private static Lock lock = new ReentrantLock();
    Map<Class, Supplier<GenericDao>> daoSupplier = new HashMap<>();

    private JdbcDaoFactory() {
        daoSupplier.put(Album.class, AlbumDao::new);
        daoSupplier.put(AlbumFeedback.class, AlbumFeedbackDao::new);
        daoSupplier.put(Author.class, AuthorDao::new);
        daoSupplier.put(Bonus.class, BonusDao::new);
        daoSupplier.put(Composition.class, CompositionDao::new);
        daoSupplier.put(CompositionFeedback.class, CompositionFeedbackDao::new);
        daoSupplier.put(Genre.class, GenreDao::new);
        daoSupplier.put(MusicSelection.class, MusicSelectionDao::new);
        daoSupplier.put(MusicSelectionInfo.class, MusicSelectionInfoDao::new);
        daoSupplier.put(MusicSelectionFeedback.class, MusicSelectionFeedbackDao::new);
        daoSupplier.put(Role.class, RoleDao::new);
        daoSupplier.put(Status.class, StatusDao::new);
        daoSupplier.put(User.class, UserDao::new);
        daoSupplier.put(UserAlbum.class, UserAlbumDao::new);
        daoSupplier.put(UserBonus.class, UserBonusDao::new);
        daoSupplier.put(UserComposition.class, UserCompositionDao::new);
        daoSupplier.put(UserMusicSelection.class, UserMusicSelectionDao::new);
    }

    public static JdbcDaoFactory getInstance() {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new JdbcDaoFactory();
            }
        } finally {
            lock.unlock();
        }

        return INSTANCE;
    }

    private class DaoInvocationHandler implements InvocationHandler {
        private final GenericDao dao;

        DaoInvocationHandler(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory
                        .getInstance()
                        .getConnectionPool(ConnectionPoolType.MYSQL);

                Connection connection = connectionPool.getConnection();

                TransactionManagerImpl.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.releaseConnection(connection);
                TransactionManagerImpl.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    @Override
    public <T extends Identified<PK>, PK extends Number> GenericDao<T, PK> getDao(Class<T> tClass) throws DaoException {
        Supplier<GenericDao> supplier = daoSupplier.get(tClass);
        if (supplier == null) {
            throw new DaoException("kek");
        }

        GenericDao dao = supplier.get();
        return (GenericDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public <T extends Identified<PK>, PK extends Number> GenericDao<T, PK> getTransactionalDao(Class<T> tClass) throws DaoException {
        Supplier<GenericDao> supplier = daoSupplier.get(tClass);

        if (supplier == null) {
            throw new DaoException("lol");
        }

        return supplier.get();
    }
}
