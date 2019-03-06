package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

public class AbstractJDBCDAOFactory {
    private final static Map<Bean, GenericDAO> commandMap = new HashMap<>();

    private AbstractJDBCDAOFactory () {
        commandMap.put(Bean.ALBUM, new AlbumDAO());
        commandMap.put(Bean.ALBUM_FEEDBACK, new AlbumFeedbackDAO());
        commandMap.put(Bean.AUTHOR, new AuthorDAO());
        commandMap.put(Bean.BONUS, new BonusDAO());
        commandMap.put(Bean.COMPOSITION, new CompositionDAO());
        commandMap.put(Bean.COMPOSITION_FEEDBACK, new CompositionFeedbackDAO());
        commandMap.put(Bean.GENRE, new GenreDAO());
        commandMap.put(Bean.MUSIC_SELECTION, new MusicSelectionDAO());
        commandMap.put(Bean.MUSIC_SELECTION_FEEDBACK, new MusicSelectionFeedbackDAO());
        commandMap.put(Bean.ROLE, new RoleDAO());
        commandMap.put(Bean.SESSION_DATA, new SessionDataDAO());
        commandMap.put(Bean.USER_ALBUM, new UserAlbumDAO());
        commandMap.put(Bean.USER_BONUS, new UserBonusDAO());
        commandMap.put(Bean.USER_COMPOSITION, new UserCompositionDAO());
        commandMap.put(Bean.USER, new UserDAO());
        commandMap.put(Bean.USER_MUSIC_SELECTION, new UserMusicSelectionDAO());
    }

    public enum Bean {
        ALBUM,
        ALBUM_FEEDBACK,
        AUTHOR,
        BONUS,
        COMPOSITION,
        COMPOSITION_FEEDBACK,
        GENRE,
        MUSIC_SELECTION,
        MUSIC_SELECTION_FEEDBACK,
        ROLE,
        SESSION_DATA,
        USER_ALBUM,
        USER_BONUS,
        USER_COMPOSITION,
        USER,
        USER_MUSIC_SELECTION
    }

    public static GenericDAO getDAO (Bean bean) {
        return commandMap.get(bean);
    }
}
