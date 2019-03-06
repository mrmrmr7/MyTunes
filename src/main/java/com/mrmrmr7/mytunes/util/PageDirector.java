package com.mrmrmr7.mytunes.util;

public enum PageDirector {
    CRUD_USER ("/jsp/crud/user.jsp"),
    CRUD_ROLE ("/jsp/crud/role.jsp"),
    CRUD_GENRE ("/jsp/crud/genre.jsp"),
    CRUD_AUTHOR ("/jsp/crud/author.jsp"),
    CRUD_BONUS ("/jsp/crud/bonus.jsp"),
    CRUD_ALBUM ("/jsp/crud/album.jsp"),
    CRUD_ALBUM_FEEDBACK ("/jsp/crud/albumfeedback.jsp"),
    LANDING ("/index.jsp"),
    VIEW ("/jsp/view.jsp"),
    ACCOUNT ("/jsp/account.jsp");

    private String value;

    PageDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
