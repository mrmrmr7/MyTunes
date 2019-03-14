package com.mrmrmr7.mytunes.util;

public enum TableName {
    USER ("users"),
    AUTHOR ("author"),
    COMPOSITION ("composition"),
    COMPOSITION_FEEDBACK ("composition_feedback"),
    ALBUM ("album"),
    ALBUM_FEEDBACK ("album_feedback"),
    BONUS ("bonus"),
    STATUS ("status"),
    GENRE ("genre"),
    ROLE ("role"),
    SESSION_DATA ("session_data"),
    MUSIC_SELECTION ("music_selection"),
    MUSIC_SELECTION_FEEDBACK ("music_selection_feedback"),
    USER_ALBUM ("user_album"),
    USER_BONUS ("user_bonus"),
    USER_COMPOSITION ("user_composition"),
    USER_MUSIC_SELECTION ("user_music_selection");

    private String value;

    TableName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
