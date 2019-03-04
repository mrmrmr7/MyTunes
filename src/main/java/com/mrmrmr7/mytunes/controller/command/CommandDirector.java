package com.mrmrmr7.mytunes.controller.command;

public enum  CommandDirector {
    COMMAND ("command");

    private String value;

    CommandDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
