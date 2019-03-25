package com.mrmrmr7.mytunes.controller;

public enum  RequestDirector {
    LOGIN ("login"),
    PASSWORD ("password"),
    EMAIL ("email"),
    FIRST_NAME ("firstName"),
    SECOND_NAME ("secondName"),
    TOKEN ("token"),
    PUBLIC_KEY("publicKey");

    private String value;

    RequestDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
