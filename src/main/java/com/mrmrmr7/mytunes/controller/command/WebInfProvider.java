package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.util.PageDirector;
import com.mrmrmr7.mytunes.util.WebInfDirector;

import java.util.HashMap;
import java.util.Map;

public class WebInfProvider {
    public static WebInfProvider INSTANCE = new WebInfProvider();
    private Map<String, String> avaliablePageMap = new HashMap<>();

    private WebInfProvider() {
        avaliablePageMap.put(WebInfDirector.CRUD_USER.getValue(), PageDirector.CRUD_USER.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_ROLE.getValue(), PageDirector.CRUD_ROLE.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_GENRE.getValue(), PageDirector.CRUD_GENRE.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_AUTHOR.getValue(), PageDirector.CRUD_AUTHOR.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_BONUS.getValue(), PageDirector.CRUD_BONUS.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_ALBUM.getValue(), PageDirector.CRUD_ALBUM.getValue());
        avaliablePageMap.put(WebInfDirector.CRUD_ALBUM_FEEDBACK.getValue(), PageDirector.CRUD_ALBUM_FEEDBACK.getValue());
        avaliablePageMap.put(WebInfDirector.VIEW.getValue(), PageDirector.VIEW.getValue());
        avaliablePageMap.put(WebInfDirector.ACCOUNT.getValue(), PageDirector.ACCOUNT.getValue());
    }

    public static WebInfProvider getInstance() {
        return INSTANCE;
    }

    public String takePath(String path) {
        return avaliablePageMap.get(path);
    }
}
