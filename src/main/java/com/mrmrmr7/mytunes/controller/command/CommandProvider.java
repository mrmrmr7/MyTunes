package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.controller.command.impl.CommandSignIn;
import com.mrmrmr7.mytunes.controller.command.impl.CommandSignUp;
import com.mrmrmr7.mytunes.controller.command.impl.author.CommandAuthorDelete;
import com.mrmrmr7.mytunes.controller.command.impl.author.CommandAuthorGetAll;
import com.mrmrmr7.mytunes.controller.command.impl.author.CommandAuthorGetById;
import com.mrmrmr7.mytunes.controller.command.impl.author.CommandAuthorInsert;
import com.mrmrmr7.mytunes.controller.command.impl.genre.CommandGenreDelete;
import com.mrmrmr7.mytunes.controller.command.impl.genre.CommandGenreGetAll;
import com.mrmrmr7.mytunes.controller.command.impl.genre.CommandGenreGetById;
import com.mrmrmr7.mytunes.controller.command.impl.genre.CommandGenreInsert;
import com.mrmrmr7.mytunes.controller.command.impl.bonus.CommandBonusDelete;
import com.mrmrmr7.mytunes.controller.command.impl.bonus.CommandBonusGetAll;
import com.mrmrmr7.mytunes.controller.command.impl.bonus.CommandBonusGetById;
import com.mrmrmr7.mytunes.controller.command.impl.bonus.CommandBonusInsert;
import com.mrmrmr7.mytunes.controller.command.impl.user.*;


import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put(CommandDirector.SIGN_IN.getValue(), new CommandSignIn());
        commandMap.put(CommandDirector.SIGN_UP.getValue(), new CommandSignUp());

        commandMap.put(CommandDirector.USER_GET_BY_ID.getValue(), new CommandUserGetById());
        commandMap.put(CommandDirector.USER_GET_ALL.getValue(), new CommandUserGetAll());
        commandMap.put(CommandDirector.USER_INSERT.getValue(), new CommandUserInsert());
        commandMap.put(CommandDirector.USER_DELETE.getValue(), new CommandUserDelete());

        commandMap.put(CommandDirector.AUTHOR_GET_BY_ID.getValue(), new CommandAuthorGetById());
        commandMap.put(CommandDirector.AUTHOR_GET_ALL.getValue(), new CommandAuthorGetAll());
        commandMap.put(CommandDirector.AUTHOR_INSERT.getValue(), new CommandAuthorInsert());
        commandMap.put(CommandDirector.AUTHOR_DELETE.getValue(), new CommandAuthorDelete());

        commandMap.put(CommandDirector.GENRE_GET_BY_ID.getValue(), new CommandGenreGetById());
        commandMap.put(CommandDirector.GENRE_GET_ALL.getValue(), new CommandGenreGetAll());
        commandMap.put(CommandDirector.GENRE_INSERT.getValue(), new CommandGenreInsert());
        commandMap.put(CommandDirector.GENRE_DELETE.getValue(), new CommandGenreDelete());

        commandMap.put(CommandDirector.ROLE_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.ROLE_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.ROLE_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.ROLE_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.ALBUM_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.ALBUM_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.ALBUM_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.ALBUM_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.ALBUM_FEEDBACK_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.ALBUM_FEEDBACK_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.ALBUM_FEEDBACK_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.ALBUM_FEEDBACK_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.BONUS_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.BONUS_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.BONUS_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.BONUS_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.COMPOSITION_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.COMPOSITION_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.COMPOSITION_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.COMPOSITION_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.COMPOSITION_FEEDBACK_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.COMPOSITION_FEEDBACK_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.COMPOSITION_FEEDBACK_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.COMPOSITION_FEEDBACK_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.MUSIC_SELECTION_FEEDBACK_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.MUSIC_SELECTION_FEEDBACK_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.MUSIC_SELECTION_FEEDBACK_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.MUSIC_SELECTION_FEEDBACK_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.MUSIC_SELECTION_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.MUSIC_SELECTION_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.MUSIC_SELECTION_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.MUSIC_SELECTION_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.STATUS_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.STATUS_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.STATUS_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.STATUS_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.USER_ALBUM_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.USER_ALBUM_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.USER_ALBUM_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.USER_ALBUM_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.USER_BONUS_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.USER_BONUS_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.USER_BONUS_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.USER_BONUS_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.USER_COMPOSITION_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.USER_COMPOSITION_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.USER_COMPOSITION_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.USER_COMPOSITION_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.USER_MUSIC_SELECTION_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.USER_MUSIC_SELECTION_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.USER_MUSIC_SELECTION_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.USER_MUSIC_SELECTION_DELETE.getValue(), new CommandBonusDelete());

        commandMap.put(CommandDirector.SESSION_DATA_GET_BY_ID.getValue(), new CommandBonusGetById());
        commandMap.put(CommandDirector.SESSION_DATA_GET_ALL.getValue(), new CommandBonusGetAll());
        commandMap.put(CommandDirector.SESSION_DATA_INSERT.getValue(), new CommandBonusInsert());
        commandMap.put(CommandDirector.SESSION_DATA_DELETE.getValue(), new CommandBonusDelete());

    }

    /**
     * Return command by name
     *
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
        return commandMap.get(command);
    }
}