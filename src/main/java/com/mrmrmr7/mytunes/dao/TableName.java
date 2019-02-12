package com.mrmrmr7.mytunes.dao;

public enum TableName {
    USER ("USERS"),
    AUTHOR ("AUTHOR");

    private String value;

    TableName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
