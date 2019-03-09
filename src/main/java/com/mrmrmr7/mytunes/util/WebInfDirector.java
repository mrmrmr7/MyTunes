package com.mrmrmr7.mytunes.util;

public enum WebInfDirector {
    CRUD_USER ("cruduser"),
    CRUD_ROLE ("crudrole"),
    CRUD_GENRE ("crudgenre"),
    CRUD_AUTHOR ("crudauthor"),
    CRUD_BONUS ("crudbonus"),
    CRUD_ALBUM ("crudalbum"),
    CRUD_ALBUM_FEEDBACK ("crudalbumfeedback"),
    VIEW ("view"),
    ACCOUNT ("account");

    private String value;

    WebInfDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
