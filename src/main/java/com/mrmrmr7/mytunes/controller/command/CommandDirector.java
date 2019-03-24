package com.mrmrmr7.mytunes.controller.command;

import java.util.Arrays;

public enum  CommandDirector {
    COMMAND ( "command","01"),
    CHANGE_LANGUAGE_COMMAND ("changeLang","02"),
    CHANGE_LANGUAGE_IN_SIGN_IN_COMMAND("changeLangInSignIn", "03"),

    MOVE_INSIDE_WEB_INF ("move","04"),
    SECRET_ACCOUNT ("secretAccount", "05"),
    UPDATE_BALANCE ("updateBalance", "06"),

    VIEW_SIGN_IN_PAGE ("viewSignInPage", "07"),
    VIEW_SIGN_UP_PAGE ("viewSignUpPage", "08"),

    //ADD
    ADD_COMPOSITION_FEEDBACK ("addCompositionFeedback", "09"),
    ADD_ALBUM_FEEDBACK ("addAlbumFeedback", "10"),
    ADD_MUSIC_SELECTION_FEEDBACK ("addMusicSelectionFeedback", "11"),

    //AUTHORIZATION OPERATIONS
    LOGIN ("login", "12"),
    PASSWORD ("password", "13"),
    SIGN_UP ("signup", "14"),
    SIGN_IN ("signin", "15"),
    LOG_OUT ("logout", "16"),
    FINISH_REGISTRATION ("finishRegistration", "17"),
    SKIP_F5_COMMAND ("skipf5command", "18"),

    //BUY OPERATIONS
    BUY_COMPOSITION ("buyComposition", "19"),
    BUY_ALBUM ("buyAlbum", "20"),
    BUY_MUSIC_SELECTION ("buyMusicSelection", "21"),

    //ADMIN
    ADMIN_ADD_USER_BONUS ("adminAddUserBonus", "22"),
    ADMIN_UPLOAD_COMPOSITION ("uploadComposition", "23"),
    ADMIN_VIEW_ADD_USER_BONUS_PAGE ("viewAdminAddUserBonusPage", "24"),
    ADMIN_VIEW_UPLOAD_COMPOSITION_PAGE ("viewUploadCompositionPage", "25"),
    ADMIN_VIEW_ADD_ALBUM_PAGE ("viewAdminCreateAlbumPage", "26"),
    ADMIN_ADD_ALBUM ("addAlbum", "27"),
    ADMIN_ADD_MUSIC_SELECTION ("addMusicSelection","28"),
    ADMIN_VIEW_CREATE_MUSIC_SELECTION_PAGE ("viewCreateMusicSelectionPage", "29"),
    ADMIN_VIEW_UPDATE_COMPOSITION_PAGE("viewUpdateCompositionPage", "30"),
    ADMIN_GET_COMPOSITION_FOR_UPDATE ("getCompositionForUpdate", "31"),
    ADMIN_UPDATE_COMPOSITION ("updateComposition", "32"),
    ADMIN_VIEW_UPDATE_MUSIC_SELECTION_PAGE ("viewUpdateMusicSelectionPage", "33"),
    ADMIN_GET_MUSIC_SELECTION_FOR_UPDATE ("getMusicSelectionForUpdate", "34"),
    ADMIN_UPDATE_MUSIC_SELECTION ("updateMusicSelection", "35"),
    ADMIN_CREATE_MUSIC_SELECTION ("createMusicSelection", "36"),
    ADMIN_VIEW_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION_PAGE ("viewUploadCompositionToMusicSelectionPage", "37"),
    ADMIN_UPLOAD_COMPOSITION_TO_MUSIC_SELECTION ("uploadCompositionToMusicSelection","38"),


    //VIEW OPERATIONS
    VIEW_PROFILE_PAGE ("account", "39"),
    VIEW_COMPOSITION_SHOP ("compositionShop", "40"),
    VIEW_ALBUM_SHOP ("albumShop", "41"),
    VIEW_MUSIC_SELECTION_SHOP ("musicSelectionShop", "42"),
    VIEW_COMPOSITION_FEEDBACK_TABLE ("viewCompositionFeedback", "43"),
    VIEW_COMPOSITION_FEEDBACK_PAGE ("viewCompositionFeedbackPage", "44"),
    VIEW_ALBUM_FEEDBACK_TABLE ("viewAlbumFeedback", "45"),
    VIEW_ALBUM_FEEDBACK_PAGE ("viewAlbumFeedbackPage", "46"),
    VIEW_MUSIC_SELECTION_FEEDBACK_TABLE ("viewMusicSelectionFeedback", "47"),
    VIEW_MUSIC_SELECTION_FEEDBACK_PAGE ("viewMusicSelectionFeedbackPage", "48"),
    VIEW_USER_MUSIC ("music", "49"),
    VIEW_UPDATE_BALANCE_PAGE ("viewUpdateBalancePage", "50"),
    VIEW_CURRENT_BALANCE_PAGE ("viewCurrentBalancePage", "51");

    private String value;
    private String code;

    CommandDirector(String value, String code) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public String getCode() {return code;}
    public static String getCodeByValue(String value) {
        return Arrays
                .stream(CommandDirector.values())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElseThrow(()->new IllegalStateException(String.format("Unsupported type %s.", value)))
                .getValue();
    }
}
