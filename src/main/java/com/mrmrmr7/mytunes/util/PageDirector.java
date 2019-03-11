package com.mrmrmr7.mytunes.util;

import java.util.Arrays;

public enum PageDirector {
    CRUD_USER ("/WEB-INF/jsp/crud/user.jsp"),
    CRUD_ROLE ("/WEB-INF/jsp/crud/role.jsp"),
    CRUD_GENRE ("/WEB-INF/jsp/crud/genre.jsp"),
    CRUD_AUTHOR ("/WEB-INF/jsp/crud/author.jsp"),
    CRUD_BONUS ("/WEB-INF/jsp/crud/bonus.jsp"),
    CRUD_ALBUM ("/WEB-INF/jsp/crud/album.jsp"),
    CRUD_ALBUM_FEEDBACK ("/WEB-INF/jsp/crud/albumfeedback.jsp"),
    LANDING ("/index.jsp"),
    REGISTRATION ("/signup.jsp"),
    VIEW ("/WEB-INF/jsp/view.jsp"),
    VIEW_NAME ("viewName"),
    ACCOUNT ("/WEB-INF/jsp/account.jsp"),
    SECRET_ACCOUNT ("/WEB-INF/jsp/saveOfAccount.jsp");

    private String value;

    PageDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PageDirector getEnum(String val) {
        return Arrays.stream(PageDirector.values())
                .filter(e -> e.value.equals(val))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", val)));
    }
}
