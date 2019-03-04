package com.mrmrmr7.mytunes.util.impl;

import com.mrmrmr7.mytunes.util.Descriptor;

public class LayerDescriptor implements Descriptor {
    private static LayerDescriptor ourInstance = new LayerDescriptor();

    public static LayerDescriptor getInstance() {
        return ourInstance;
    }

    private LayerDescriptor() {
    }

    @Override
    public String descript(String string) {
        switch (string) {
            case "1": return "view";
            case "2": return "controller";
            case "3": return "service";
            case "4": return "dao";
            default: return "";
        }
    }
}
