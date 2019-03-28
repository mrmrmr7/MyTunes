package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.AbstractJdbcDao;
import com.mrmrmr7.mytunes.dao.AutoConnection;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionDao extends AbstractJdbcDao<MusicSelection, Integer> implements GenericDao<MusicSelection, Integer> {

    public MusicSelectionDao() {
    }

    @AutoConnection
    @Override
    public Optional<MusicSelection> getByPK(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toMusicSelection(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1061));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1062));
        }
    }

    @AutoConnection
    @Override
    public List<MusicSelection> getAll() throws DaoException {

        List<MusicSelection> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.MUSIC_SELECTION)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toMusicSelection(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1063));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1064));
        }

        return userList;
    }

    @Override
    @AutoConnection
    public void insert(MusicSelection object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1065));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1066));
        }
    }

    @AutoConnection
    @Override
    public void update(MusicSelection object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1067));
        }
    }

    @AutoConnection
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

    @AutoConnection
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

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.MUSIC_SELECTION));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.MUSIC_SELECTION), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected String getSelectQuery(Table table) {

        return "SELECT * FROM " + table.getValue() + " WHERE MUSIC_SELECTION_ID=?";
    }

    @AutoConnection
    @Override
    protected String getDeleteQuery(Table table) {
        return "DELETE FROM " + table.getValue() + " WHERE MUSIC_SELECTION_ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.MUSIC_SELECTION.getValue() +
                "(MUSIC_SELECTION_ID, COMPOSITION_ID) " +
                "VALUES " +
                "(?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.MUSIC_SELECTION.getValue() + " SET " +
                "MUSIC_SELECTION_ID=?, COMPOSITION_ID=? " +
                "WHERE ID=?";

    }
}
