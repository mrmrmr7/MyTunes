package com.mrmrmr7.mytunes.util.impl;

import com.mrmrmr7.mytunes.util.Descriptor;

public class MethodDAODescriptor implements Descriptor {

    @Override
    public String descript(String code) {
        switch (code) {
            case "1": return "Impossible to do get query";
            case "2": return "Impossible to prepare statement";
            case "3": return "Impossible to do detAll statement";
            case "4": return "Impossible to prepare statement";
            case "5": return "Impossible to insert statement";
            case "6": return "Impossible to delete statement";
            case "7": return "Impossible update statement";
            default: return null;
        }
    }
}
