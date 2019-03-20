package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.controller.command.impl.*;
import com.mrmrmr7.mytunes.controller.command.impl.view.*;


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
        commandMap.put(CommandDirector.SIGN_IN.getValue(), new SignInCommand());
        commandMap.put(CommandDirector.SIGN_UP.getValue(), new SignUpStartCommand());
        commandMap.put(CommandDirector.LOG_OUT.getValue(), new LogOutCommand());

        commandMap.put(CommandDirector.FINISH_REGISTRATION.getValue(), new SignUpFinishCommand());

        commandMap.put(CommandDirector.VIEW_PROFILE_PAGE.getValue(), new ViewProfileCommand());

        commandMap.put(CommandDirector.VIEW_COMPOSITION_SHOP.getValue(), new ViewCompositionShopCommand());
        commandMap.put(CommandDirector.VIEW_ALBUM_SHOP.getValue(), new ViewAlbumShopCommand());
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_SHOP.getValue(), new ViewMusicSelectionShopCommand());

        commandMap.put(CommandDirector.VIEW_USER_MUSIC.getValue(), new ViewUserMusicCommand());
        commandMap.put(CommandDirector.BUY_COMPOSITION.getValue(), new BuyCompositionCommand());
        commandMap.put(CommandDirector.BUY_ALBUM.getValue(), new BuyAlbumCommand());
        commandMap.put(CommandDirector.BUY_MUSIC_SELECTION.getValue(), new BuyMusicSelectionCommand());

        commandMap.put(CommandDirector.VIEW_COMPOSITION_FEEDBACK_PAGE.getValue(), new ViewCompositionFeedbackPageCommand());
        commandMap.put(CommandDirector.VIEW_COMPOSITION_FEEDBACK_TABLE.getValue(), new ViewCompositionFeedbackCommand());
        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_PAGE.getValue(), new ViewAlbumFeedbackPageCommand());
        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_TABLE.getValue(), new ViewAlbumFeedbackCommand());
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_PAGE.getValue(), new ViewMusicSelectionFeedbackPageCommand());
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_TABLE.getValue(), new ViewMusicSelectionFeedbackCommand());
        commandMap.put(CommandDirector.ADD_COMPOSITION_FEEDBACK.getValue(), new AddCompositionFeedbackCommand());

        commandMap.put(CommandDirector.VIEW_CURRENT_BALANCE_PAGE.getValue(), new ViewCurrentBalanceCommand());
        commandMap.put(CommandDirector.VIEW_UPDATE_BALANCE_PAGE.getValue(), new ViewUpdateBalancePageCommand());
        commandMap.put(CommandDirector.UPDATE_BALANCE.getValue(), new UpdateBalanceCommand());

        commandMap.put(CommandDirector.ADMIN_ADD_USER_BONUS.getValue(), new AdminAddUserBonusCommand());
        commandMap.put(CommandDirector.ADMIN_ADD_ALBUM.getValue(), new AdminAddAlbumCommand());
        commandMap.put(CommandDirector.ADMIN_ADD_MUSIC_SELECTION.getValue(), new AdminAddMusicSelectionCommand());
        commandMap.put(CommandDirector.ADMIN_UPLOAD_COMPOSITION.getValue(), new AdminCompositionUploadCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_ADD_USER_BONUS_PAGE.getValue(), new ViewAdminAddUserBonusPageCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_ADD_ALBUM_PAGE.getValue(), new ViewAdminCreateAlbumPage());
        commandMap.put(CommandDirector.ADMIN_VIEW_UPLOAD_COMPOSITION_PAGE.getValue(), new ViewCompositionUploadPageCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_CREATE_MUSIC_SELECTION_PAGE.getValue(), new ViewCreateMusicSelectionPageCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_UPDATE_COMPOSITION_PAGE.getValue(), new ViewUpdateCompositionPageCommand());
        commandMap.put(CommandDirector.ADMIN_GET_COMPOSITION_FOR_UPDATE.getValue(), new GetCompositionForUpdateCommand());
        commandMap.put(CommandDirector.ADMIN_UPDATE_COMPOSITION.getValue(), new UpdateCompositionCommand());

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