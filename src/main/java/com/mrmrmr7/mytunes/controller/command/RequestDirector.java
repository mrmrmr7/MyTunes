package com.mrmrmr7.mytunes.controller.command;

public enum  RequestDirector {
    LOGIN ("login"),
    PASSWORD ("password"),
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
