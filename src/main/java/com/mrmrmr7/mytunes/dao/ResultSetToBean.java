package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToBean {

    public User toUser(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new User(resultSet.getInt(++i),
                        resultSet.getDate(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i).toLowerCase(),
                        resultSet.getLong(++i),
                        resultSet.getByte(++i),
                        resultSet.getByte(++i),
                        resultSet.getByte(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i));
    }

    public Author toAuthor(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Author(resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getString(++i),
                resultSet.getString(++i));
    }

    public Composition toComposition(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Composition(resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i));
    }

    public CompositionFeedback toCompositionFeedback(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new CompositionFeedback(
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getTimestamp(++i)
        );
    }

    public AlbumFeedback toAlbumFeedback(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new AlbumFeedback(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Bonus toBonus(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Bonus(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Status toStatus(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Status(
                resultSet.getByte(++i),
                resultSet.getString(++i)
        );
    }

    public Genre toGenre(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Genre(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Role toRole(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Role(
                resultSet.getByte(++i),
                resultSet.getString(++i).toLowerCase()
        );
    }

    public Album toAlbum(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Album(
                resultSet.getInt(++i),
                resultSet.getLong(++i),
                resultSet.getString(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );
    }

    public MusicSelection toMusicSelection(ResultSet resultSet) throws SQLException {

        final int SELECTION_ID_COL_NUM = 2;
        final int ID_COL_NUM = 1;

        int thisMusicSelection = resultSet.getInt(SELECTION_ID_COL_NUM);

        int i = 0;
        MusicSelection musicSelection = new MusicSelection(
                resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );

        while (resultSet.next() && (resultSet.getInt(SELECTION_ID_COL_NUM) == thisMusicSelection)) {
            musicSelection.addCompositionId(resultSet.getInt(ID_COL_NUM),resultSet.getInt(i));
        }

        resultSet.previous();

        return musicSelection;
    }

    public MusicSelectionFeedback toMusicSelectionFeedback(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new MusicSelectionFeedback(
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getTimestamp(++i)
        );
    }

    public UserAlbum toUserAlbum(ResultSet resultSet) throws SQLException {

        final int ALBUM_ID_COL_NUM = 2;
        final int ID_COL_NUM = 1;

        int thisMusicSelection = resultSet.getInt(ALBUM_ID_COL_NUM);

        int i = 0;
        UserAlbum musicSelection = new UserAlbum(
                resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );

        while (resultSet.next() && (resultSet.getInt(ALBUM_ID_COL_NUM) == thisMusicSelection)) {
            musicSelection.addCompositionId(resultSet.getInt(ID_COL_NUM),resultSet.getInt(i));
        }

        resultSet.previous();

        return musicSelection;
    }

    public UserBonus toUserBonus(ResultSet resultSet) throws SQLException {

        final int USER_ID_COL_NUM = 2;
        final int ID_COL_NUM = 1;

        int thisMusicSelection = resultSet.getInt(USER_ID_COL_NUM);

        int i = 0;
        UserBonus musicSelection = new UserBonus(
                resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );

        while (resultSet.next() && (resultSet.getInt(USER_ID_COL_NUM) == thisMusicSelection)) {
            musicSelection.addBonusId(resultSet.getInt(ID_COL_NUM),resultSet.getInt(i));
        }

        resultSet.previous();

        return musicSelection;
    }

    public UserComposition toUserComposition(ResultSet resultSet) throws SQLException {

        final int ALBUM_ID_COL_NUM = 2;
        final int ID_COL_NUM = 1;

        int thisMusicSelection = resultSet.getInt(ALBUM_ID_COL_NUM);

        int i = 0;
        UserComposition musicSelection = new UserComposition(
                resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );

        while (resultSet.next() && (resultSet.getInt(ALBUM_ID_COL_NUM) == thisMusicSelection)) {
            musicSelection.addCompositionId(resultSet.getInt(ID_COL_NUM),resultSet.getInt(i));
        }

        resultSet.previous();

        return musicSelection;
    }

    public UserMusicSelection toUserMusicSelection(ResultSet resultSet) throws SQLException {

        final int ALBUM_ID_COL_NUM = 2;
        final int ID_COL_NUM = 1;

        int thisMusicSelection = resultSet.getInt(ALBUM_ID_COL_NUM);

        int i = 0;
        UserMusicSelection musicSelection = new UserMusicSelection(
                resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );

        while (resultSet.next() && (resultSet.getInt(ALBUM_ID_COL_NUM) == thisMusicSelection)) {
            musicSelection.addMusicSelection(resultSet.getInt(ID_COL_NUM),resultSet.getInt(i));
        }

        resultSet.previous();

        return musicSelection;
    }
}

