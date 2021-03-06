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
        commandMap.put(CommandDirector.CHANGE_LANGUAGE_COMMAND.getValue(), new ChangeLanguageCommand());
        commandMap.put(CommandDirector.CHANGE_LANGUAGE_IN_SIGN_IN_COMMAND.getValue(), new ChangeLanguageInSignUpCommand());

        commandMap.put(CommandDirector.VIEW_SIGN_IN_PAGE.getValue(), new ViewSignInPageCommand());
        commandMap.put(CommandDirector.VIEW_SIGN_UP_PAGE.getValue(), new ViewSignUpPageCommand());
        commandMap.put(CommandDirector.VIEW_FORGET_PASSWORD_PAGE.getValue(), new ViewForgetPasswordPageCommand());
        commandMap.put(CommandDirector.VIEW_RESTART_PASSWORD_PAGE.getValue(), new ViewRestartPasswordPage());
        commandMap.put(CommandDirector.START_RESTART_PASSWORD.getValue(), new StartRestartPasswordCmmand());
        commandMap.put(CommandDirector.FINISH_RESTART_PASSWORD.getValue(), new FinishRestartPasswordCommand());

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
        commandMap.put(CommandDirector.ADD_COMPOSITION_FEEDBACK.getValue(), new AddCompositionFeedbackCommand());

        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_PAGE.getValue(), new ViewAlbumFeedbackPageCommand());
        commandMap.put(CommandDirector.VIEW_ALBUM_FEEDBACK_TABLE.getValue(), new ViewAlbumFeedbackCommand());
        commandMap.put(CommandDirector.ADD_ALBUM_FEEDBACK.getValue(), new AddAlbumFeedbackCommand());

        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_PAGE.getValue(), new ViewMusicSelectionFeedbackPageCommand());
        commandMap.put(CommandDirector.VIEW_MUSIC_SELECTION_FEEDBACK_TABLE.getValue(), new ViewMusicSelectionFeedbackCommand());
        commandMap.put(CommandDirector.ADD_MUSIC_SELECTION_FEEDBACK.getValue(), new AddMusicSelectionFeedbackCommand());

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
        commandMap.put(CommandDirector.ADMIN_GET_MUSIC_SELECTION_FOR_UPDATE.getValue(), new GetMusicSelectionForUpdateCommand());
        commandMap.put(CommandDirector.ADMIN_UPDATE_MUSIC_SELECTION.getValue(), new UpdateMusicSelectionCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_UPDATE_MUSIC_SELECTION_PAGE.getValue(), new ViewUpdateMusicSelectionPageCommand());
        commandMap.put(CommandDirector.ADMIN_CREATE_MUSIC_SELECTION.getValue(), new AdminCreateMusicSelectionCommand());
        commandMap.put(CommandDirector.ADMIN_VIEW_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION_PAGE.getValue(), new ViewUploadCompositionToMusicSelectionPageCommand());
        commandMap.put(CommandDirector.ADMIN_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION.getValue(), new AdminUploadCompositionToMusicSelectionCommand());
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