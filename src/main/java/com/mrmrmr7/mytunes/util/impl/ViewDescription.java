package com.mrmrmr7.mytunes.util.impl;

import com.mrmrmr7.mytunes.util.Descriptor;

public class ViewDescription implements Descriptor {
    private final static ViewDescription INSTANCE = new ViewDescription();

    private ViewDescription() {}

    @Override
    public String descript(String code) {
        return null;
    }

    public static ViewDescription getInstance() {
        return INSTANCE;
    }
}
