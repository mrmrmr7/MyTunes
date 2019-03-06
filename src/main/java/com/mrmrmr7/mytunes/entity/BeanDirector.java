package com.mrmrmr7.mytunes.entity;

public enum BeanDirector {
    ALBUM ("album"),
    ALBUM_FEEDBACK ("albumFeedback"),
    AUTHOR ("author"),
    BONUS ("bonus"),
    GENRE ("genre"),
    USER ("user"),
    ROLE ("role"),
    ACCOUNT ("/jsp/account.jsp");

    private String value;

    BeanDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
