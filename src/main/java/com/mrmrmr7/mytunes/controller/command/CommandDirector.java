package com.mrmrmr7.mytunes.controller.command;

public enum  CommandDirector {
    COMMAND ("command"),
    CHANGE_LANGUAGE_COMMAND ("changeLang"),

    MOVE_INSIDE_WEB_INF ("move"),
    SECRET_ACCOUNT ("secretAccount"),
    UPDATE_BALANCE ("updateBalance"),

    VIEW_SIGN_IN_PAGE ("viewSignInPage"),
    VIEW_SIGN_UP_PAGE ("viewSignUpPage"),

    //ADD
    ADD_COMPOSITION_FEEDBACK ("addCompositionFeedback"),
    ADD_ALBUM_FEEDBACK ("addAlbumFeedback"),
    ADD_MUSIC_SELECTION_FEEDBACK ("addMusicSelectionFeedback"),

    //AUTHORIZATION OPERATIONS
    LOGIN ("login"),
    PASSWORD ("password"),
    SIGN_UP ("signup"),
    SIGN_IN ("signin"),
    LOG_OUT ("logout"),
    FINISH_REGISTRATION ("finishRegistration"),

    //BUY OPERATIONS
    BUY_COMPOSITION ("buyComposition"),
    BUY_ALBUM ("buyAlbum"),
    BUY_MUSIC_SELECTION ("buyMusicSelection"),

    //ADMIN
    ADMIN_ADD_USER_BONUS ("adminAddUserBonus"),
    ADMIN_UPLOAD_COMPOSITION ("uploadComposition"),
    ADMIN_VIEW_ADD_USER_BONUS_PAGE ("viewAdminAddUserBonusPage"),
    ADMIN_VIEW_UPLOAD_COMPOSITION_PAGE ("viewUploadCompositionPage"),
    ADMIN_VIEW_ADD_ALBUM_PAGE ("viewAdminCreateAlbumPage"),
    ADMIN_ADD_ALBUM ("addAlbum"),
    ADMIN_ADD_MUSIC_SELECTION ("addMusicSelection"),
    ADMIN_VIEW_CREATE_MUSIC_SELECTION_PAGE ("viewCreateMusicSelectionPage"),
    ADMIN_VIEW_UPDATE_COMPOSITION_PAGE("viewUpdateCompositionPage"),
    ADMIN_GET_COMPOSITION_FOR_UPDATE ("getCompositionForUpdate"),
    ADMIN_UPDATE_COMPOSITION ("updateComposition"),
    ADMIN_VIEW_UPDATE_MUSIC_SELECTION_PAGE ("viewUpdateMusicSelectionPage"),
    ADMIN_GET_MUSIC_SELECTION_FOR_UPDATE ("getMusicSelectionForUpdate"),
    ADMIN_UPDATE_MUSIC_SELECTION ("updateMusicSelection"),
    ADMIN_CREATE_MUSIC_SELECTION ("createMusicSelection"),
    ADMIN_VIEW_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION_PAGE ("viewUploadCompositionToMusicSelectionPage"),
    ADMIN_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION ("uploadCompositionToMusicSelection"),


    //VIEW OPERATIONS
    VIEW_PROFILE_PAGE ("account"),
    VIEW_COMPOSITION_SHOP ("compositionShop"),
    VIEW_ALBUM_SHOP ("albumShop"),
    VIEW_MUSIC_SELECTION_SHOP ("musicSelectionShop"),
    VIEW_COMPOSITION_FEEDBACK_TABLE ("viewCompositionFeedback"),
    VIEW_COMPOSITION_FEEDBACK_PAGE ("viewCompositionFeedbackPage"),
    VIEW_ALBUM_FEEDBACK_TABLE ("viewAlbumFeedback"),
    VIEW_ALBUM_FEEDBACK_PAGE ("viewAlbumFeedbackPage"),
    VIEW_MUSIC_SELECTION_FEEDBACK_TABLE ("viewMusicSelectionFeedback"),
    VIEW_MUSIC_SELECTION_FEEDBACK_PAGE ("viewMusicSelectionFeedbackPage"),
    VIEW_USER_MUSIC ("music"),
    VIEW_UPDATE_BALANCE_PAGE ("viewUpdateBalancePage"),
    VIEW_CURRENT_BALANCE_PAGE ("viewCurrentBalancePage");

    private String value;

    CommandDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
