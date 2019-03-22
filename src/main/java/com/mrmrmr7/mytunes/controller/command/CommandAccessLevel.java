package com.mrmrmr7.mytunes.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandAccessLevel {
    private static CommandAccessLevel instance = new CommandAccessLevel();
    private Map<String, AccessLevel> commandMap = new HashMap<>();

    public static CommandAccessLevel getInstance() {
        return instance;
    }

    private CommandAccessLevel() {
        commandMap.put(CommandDirector.SIGN_IN.getValue(), AccessLevel.ALL);
        commandMap.put(CommandDirector.SIGN_UP.getValue(), AccessLevel.ALL);
        commandMap.put(CommandDirector.LOG_OUT.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), AccessLevel.ALL);
        commandMap.put(CommandDirector.VIEW_SIGN_UP_PAGE.getValue(), AccessLevel.ALL);
        commandMap.put(CommandDirector.CHANGE_LANGUAGE_COMMAND.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.CHANGE_LANGUAGE_IN_SIGN_IN_COMMAND.getValue(), AccessLevel.ALL);

        commandMap.put(CommandDirector.FINISH_REGISTRATION.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_PROFILE_PAGE.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_COMPOSITION_SHOP.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_ALBUM_SHOP.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_SHOP.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_USER_MUSIC.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.BUY_COMPOSITION.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.BUY_ALBUM.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.BUY_MUSIC_SELECTION.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_COMPOSITION_FEEDBACK_PAGE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_COMPOSITION_FEEDBACK_TABLE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.ADD_COMPOSITION_FEEDBACK.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_PAGE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_TABLE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.ADD_ALBUM_FEEDBACK.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_PAGE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_TABLE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.ADD_MUSIC_SELECTION_FEEDBACK.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.VIEW_CURRENT_BALANCE_PAGE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.VIEW_UPDATE_BALANCE_PAGE.getValue(), AccessLevel.USER);
        commandMap.put(CommandDirector.UPDATE_BALANCE.getValue(), AccessLevel.USER);

        commandMap.put(CommandDirector.ADMIN_ADD_USER_BONUS.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_ADD_ALBUM.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_ADD_MUSIC_SELECTION.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_UPLOAD_COMPOSITION.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_ADD_USER_BONUS_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_ADD_ALBUM_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_UPLOAD_COMPOSITION_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_CREATE_MUSIC_SELECTION_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_UPDATE_COMPOSITION_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_GET_COMPOSITION_FOR_UPDATE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_UPDATE_COMPOSITION.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_GET_MUSIC_SELECTION_FOR_UPDATE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_UPDATE_MUSIC_SELECTION.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_UPDATE_MUSIC_SELECTION_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_CREATE_MUSIC_SELECTION.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_VIEW_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION_PAGE.getValue(), AccessLevel.ADMIN);
        commandMap.put(CommandDirector.ADMIN_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION.getValue(), AccessLevel.ADMIN);
    }

    public AccessLevel showLevel(String command) {
        return commandMap.get(command);
    }
}
