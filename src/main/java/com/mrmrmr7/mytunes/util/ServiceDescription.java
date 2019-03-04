package com.mrmrmr7.mytunes.util;

public class ServiceDescription implements Descriptor {
    private final static ServiceDescription INSTANCE = new ServiceDescription();

    private ServiceDescription() {}

    public static Descriptor getInstance() {
        return INSTANCE;
    }

    @Override
    public String descript(String code) {
        return null;
    }
}
