package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJDBCDAO;
import com.mrmrmr7.mytunes.dao.GenericDAO;
import com.mrmrmr7.mytunes.dao.TableName;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.MusicSelection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionDAO extends AbstractJDBCDAO<MusicSelection, Integer> implements GenericDAO<MusicSelection, Integer> {

    public MusicSelectionDAO() {
    }

    @Override
    public Optional<MusicSelection> getByPK(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return Optional.of(resultSetCompiller.setMusicSelection(resultSet));
        }
    }

    @Override
    public List<MusicSelection> getAll() throws SQLException {

        List<MusicSelection> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(TableName.MUSIC_SELECTION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetCompiller.setMusicSelection(resultSet));
                }
            }
        }

        return userList;
    }

    @Override
    public void insert(MusicSelection object) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(MusicSelection object) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        }
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(MusicSelection object) throws SQLException {

        List<Integer> compositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());

        for (int i = 0; i < compositionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getCompositionId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(MusicSelection object) throws SQLException {

        List<Integer> compositionIdList = object.getCompositionIdList();
        PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery());

        for (int i = 0; i < compositionIdList.size(); i++) {
            int j = 0;
            preparedStatement.setInt(++j, object.getId());
            preparedStatement.setInt(++j, object.getCompositionId(i));
            preparedStatement.setInt(++j, object.getCortageId(i));
            preparedStatement.addBatch();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(TableName.MUSIC_SELECTION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(TableName.MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    protected String getSelectQuery(TableName tableName) {

        return "SELECT * FROM " + tableName.getValue() + " WHERE SELECTION_ID=?";
    }

    @Override
    protected String getDeleteQuery(TableName tableName) {
        return "DELETE FROM " + tableName.getValue() + " WHERE SELECTION_ID=?";
    }


    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + TableName.MUSIC_SELECTION.getValue() +
                "(SELECTION_ID, COMPOSITION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @Override
    public String getUpdateQuery() {

        return "UPDATE " + TableName.MUSIC_SELECTION.getValue() + " SET " +
                "SELECTION_ID=?, COMPOSITION_ID=? " +
                "WHERE ID=?";

    }
}
