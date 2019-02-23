package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
}
