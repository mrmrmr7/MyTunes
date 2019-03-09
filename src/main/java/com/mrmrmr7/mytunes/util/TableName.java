package com.mrmrmr7.mytunes.util;

public enum TableName {
    USER ("USERS"),
    AUTHOR ("AUTHOR"),
    COMPOSITION ("COMPOSITION"),
    COMPOSITION_FEEDBACK ("COMPOSITION_FEEDBACK"),
    ALBUM ("ALBUM"),
    ALBUM_FEEDBACK ("ALBUM_FEEDBACK"),
    BONUS ("BONUS"),
    STATUS ("STATUS"),
    GENRE ("GENRE"),
    ROLE ("ROLE"),
    SESSION_DATA ("SESSION_DATA"),
    MUSIC_SELECTION ("MUSIC_SELECTION"),
    MUSIC_SELECTION_FEEDBACK ("MUSIC_SELECTION_FEEDBACK"),
    USER_ALBUM ("USER_ALBUM"),
    USER_BONUS ("USER_BONUS"),
    USER_COMPOSITION ("USER_COMPOSITION"),
    USER_MUSIC_SELECTION ("user_music_selection");

    private String value;

    TableName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
