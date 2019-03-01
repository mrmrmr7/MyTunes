package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.controller.command.author.CommandAuthorDelete;
import com.mrmrmr7.mytunes.controller.command.author.CommandAuthorGetAll;
import com.mrmrmr7.mytunes.controller.command.author.CommandAuthorGetById;
import com.mrmrmr7.mytunes.controller.command.author.CommandAuthorInsert;
import com.mrmrmr7.mytunes.controller.command.genre.CommandGenreDelete;
import com.mrmrmr7.mytunes.controller.command.genre.CommandGenreGetAll;
import com.mrmrmr7.mytunes.controller.command.genre.CommandGenreGetById;
import com.mrmrmr7.mytunes.controller.command.genre.CommandGenreInsert;
import com.mrmrmr7.mytunes.controller.command.user.*;


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
        commandMap.put("userGetById", new CommandUserGetById());
        commandMap.put("userGetAll", new CommandUserGetAll());
        commandMap.put("userInsert", new CommandUserInsert());
        commandMap.put("userDelete", new CommandUserDelete());
        commandMap.put("authorGetById", new CommandAuthorGetById());
        commandMap.put("authorGetAll", new CommandAuthorGetAll());
        commandMap.put("authorInsert", new CommandAuthorInsert());
        commandMap.put("authorDelete", new CommandAuthorDelete());
        commandMap.put("genreGetById", new CommandGenreGetById());
        commandMap.put("genreGetAll", new CommandGenreGetAll());
        commandMap.put("genreInsert", new CommandGenreInsert());
        commandMap.put("genreDelete", new CommandGenreDelete());
//        commandMap.put("main", new ShowEmptyMainPageCommand());
//        commandMap.put("register_cocktail", new CreateNewCocktailCommand());
//        commandMap.put("cocktail_list", new ViewCocktailListCommand());
//        commandMap.put("view_cocktail_details", new ViewCocktailDetailCommand());
//        commandMap.put("delete_cocktail", new DeleteCocktailCommand());
//        commandMap.put("loginmain", new TryLoginCommand());
//        commandMap.put("registration", new RegistrationCommand());
//        commandMap.put("show_main_page", new ShowLoginPageCommand());
//        commandMap.put("show_success_page", new ShowSuccessPageCommand());
//        commandMap.put("send_recovery_message",new RecoverySendMessageCommand());
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