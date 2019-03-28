package com.mrmrmr7.mytunes.util;

public class   ExceptionDirector {
    public static final String EXC_MSG = "Code:[{0}]";
    public final static String IMPOSSIBLE_GET = "2001";
    public final static String IMPOSSIBLE_ROLL_BACK = "2002";
    public final static String IMPOSSIBLE_END_TRANSACTION = "2003";
    public final static String IMPOSSIBLE_GET_DAO = "2004";
    public final static String IMPOSSIBLE_GET_DATA = "2005";
    public final static String INVALID_DATA = "2006";
    public final static String IMPOSSIBLE_SEND_MESSAGE = "2007";
    public final static String INVALID_TOKEN = "2008";
    public final static String INVALID_KEY = "2009";

    public static String getMessageByCode(String code) {
        switch (code) {
            case IMPOSSIBLE_GET : return "Impossible to get data from server";
            case IMPOSSIBLE_ROLL_BACK : return "Impossible to roll back commit";
            case IMPOSSIBLE_END_TRANSACTION : return "Impossible to end transaction";
            case IMPOSSIBLE_GET_DATA : return "Impossible to get data";
            case IMPOSSIBLE_GET_DAO : return "Impossible get dao";
            case IMPOSSIBLE_SEND_MESSAGE : return "Could not send message";
            case INVALID_DATA : return "You write too bad data";
            case INVALID_KEY : return "Cookies was corrupted";
            case INVALID_TOKEN : return "Cookie was corrupted";
            default: return "Unknown error";
        }
    }
}
