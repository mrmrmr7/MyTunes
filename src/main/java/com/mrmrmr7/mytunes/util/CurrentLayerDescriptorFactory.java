package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.util.impl.DAODescriptor;
import com.mrmrmr7.mytunes.util.impl.ViewDescription;

public class CurrentLayerDescriptorFactory {
    public static Descriptor getDecsriptor(String code) {
        switch (code) {
            case "1": return ViewDescription.getInstance();
            case "2": return ControllerDescription.getInstance();
            case "3": return ServiceDescription.getInstance();
            case "4": return DAODescriptor.getInstance();
            default: return null;
        }
    }
}
