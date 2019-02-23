package com.mrmrmr7.mytunes.dao;

public enum TableName {
    USER ("USERS"),
    AUTHOR ("AUTHOR"),
    COMPOSITION ("COMPOSITION"),
    COMPOSITION_FEEDBACK ("composition_feedback"),
    BONUS ("BONUS"),
    STATUS ("STATUS"),
    GENRE ("GENRE"),
    ROLE ("ROLE"),
    ALBUM ("ALBUM");

    private String value;

    TableName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
