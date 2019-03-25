package com.mrmrmr7.mytunes.builder;

import com.mrmrmr7.mytunes.builder.impl.UserBuilder;
import com.mrmrmr7.mytunes.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuilderFactory {private static BuilderFactory instance;
    private static Lock lock = new ReentrantLock();
    private static Map<Class, Builder> builderMap = new HashMap<>();

    public static BuilderFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new BuilderFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }


    private BuilderFactory() {
        builderMap.put(User.class, new UserBuilder());
    }

    public Builder getBuilder(Class type) {
        return builderMap.get(type);
    }

}
