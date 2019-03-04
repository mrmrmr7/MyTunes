package com.mrmrmr7.mytunes.util;

public class ControllerDescription implements Descriptor {
    private final static ControllerDescription INSTANCE = new ControllerDescription();

    private ControllerDescription() {}

    public static Descriptor getInstance() {
        return INSTANCE;
    }

    @Override
    public String descript(String code) {
        return null;
    }
}
