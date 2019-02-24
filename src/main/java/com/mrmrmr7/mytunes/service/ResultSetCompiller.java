package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSetCompiller {
    public User setUser(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new User(resultSet.getInt(++i),
                        resultSet.getDate(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getString(++i),
                        resultSet.getLong(++i),
                        resultSet.getByte(++i),
                        resultSet.getByte(++i),
                        resultSet.getByte(++i));
    }

    public Author setAuthor(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Author(resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getString(++i),
                resultSet.getString(++i));
    }

    public Composition setComposition(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Composition(resultSet.getInt(++i),
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i));
    }

    public CompositionFeedback setCompositionFeedback(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new CompositionFeedback(
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getTimestamp(++i)
        );
    }

    public AlbumFeedback setAlbumFeedback(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new AlbumFeedback(
                resultSet.getInt(++i),
                resultSet.getString(++i),
                resultSet.getTimestamp(++i)
        );
    }

    public Bonus setBonus(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Bonus(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Status setStatus(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Status(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Genre setGenre(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Genre(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Role setRole(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Role(
                resultSet.getInt(++i),
                resultSet.getString(++i)
        );
    }

    public Album setAlbum(ResultSet resultSet) throws SQLException {
        int i = 0;
        return new Album(
                resultSet.getInt(++i),
                resultSet.getLong(++i),
                resultSet.getString(++i),
                resultSet.getInt(++i),
                resultSet.getInt(++i)
        );
    }

    public MusicSelection setMusicSelection(ResultSet resultSet) throws SQLException {
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
}
