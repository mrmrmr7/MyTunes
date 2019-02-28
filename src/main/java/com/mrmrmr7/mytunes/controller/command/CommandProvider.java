package com.mrmrmr7.mytunes.controller.command;

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
        commandMap.put("getUser", new GetUserCommand());
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