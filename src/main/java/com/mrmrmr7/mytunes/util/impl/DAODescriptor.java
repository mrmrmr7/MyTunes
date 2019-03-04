package com.mrmrmr7.mytunes.util.impl;

import com.mrmrmr7.mytunes.util.Descriptor;

public class DAODescriptor implements Descriptor {
    private static DAODescriptor ourInstance = new DAODescriptor();

    public static DAODescriptor getInstance() {
        return ourInstance;
    }

    private DAODescriptor() {
    }

    @Override
    public String descript(String string) {
        switch (string) {
            case "1": return "Album";
            case "2": return "Album feedback";
            case "3": return "Author";
            case "4": return "Bonus";
            case "5": return "Composition";
            case "6": return "Composition feedback";
            case "7": return "Genre";
            case "8": return "Music selection";
            case "9": return "Music selection feedback";
            case "10": return "Role";
            case "11": return "Status";
            case "12": return "User album";
            case "13": return "User bonus";
            case "14": return "User composition";
            case "15": return "User";
            case "16": return "User music-selection";
            default: return null;
        }
    }
}
