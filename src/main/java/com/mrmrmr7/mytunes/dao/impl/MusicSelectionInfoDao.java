package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.*;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicSelectionInfoDao extends AbstractJdbcDao<MusicSelectionInfo, Integer> implements MusicSelectionInfoDaoExtended {

    @AutoConnection
    @Override
    public Optional<MusicSelectionInfo> getByPK(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForGet(id)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return Optional.of(resultSetToBean.toMusicSelectionInfo(resultSet));
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1075));
            }

        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1076));
        }

    }

    @AutoConnection
    @Override
    public Optional<MusicSelectionInfo> getByName(String name) throws DaoException {
        try (PreparedStatement preparedStatement = this.prepareStatementForGetByName(name)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                Optional<MusicSelectionInfo> musicSelectionInfo = Optional.of(resultSetToBean.toMusicSelectionInfo(resultSet));
                return musicSelectionInfo;
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1077));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1078));
        }
    }

    @AutoConnection
    private PreparedStatement prepareStatementForGetByName(String name) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectByNameQuery(Table.MUSIC_SELECTION_INFO));
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @AutoConnection
    private String getSelectByNameQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE NAME=?";
    }

    @AutoConnection
    @Override
    public List<MusicSelectionInfo> getAll() throws DaoException {

        List<MusicSelectionInfo> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatementForGetAll(Table.MUSIC_SELECTION_INFO)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList
                            .add(resultSetToBean.toMusicSelectionInfo(resultSet));
                }
            } catch (SQLException e) {
                throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1079));
            }
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1080));
        }

        return userList;
    }

    @AutoConnection
    @Override
    public void insert(MusicSelectionInfo object) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForInsert(object)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1081));
        }
    }

    @AutoConnection
    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = prepareStatementForDelete(id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1082));
        }
    }

    @AutoConnection
    @Override
    public void update(MusicSelectionInfo object) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatementForUpdate(object)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageFormat.format(ExceptionDirector.EXC_MSG, 1083));
        }
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForInsert(MusicSelectionInfo object) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery());
        return prepareForUpdate(preparedStatement, object);
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForUpdate(MusicSelectionInfo object) throws SQLException {

        PreparedStatement preparedStatement = prepareForUpdate(
                connection.prepareStatement(getUpdateQuery()),
                object);
        preparedStatement.setInt(4, object.getId());
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForDelete(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery(Table.MUSIC_SELECTION_INFO));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    @Override
    protected PreparedStatement prepareStatementForGet(Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(getSelectQuery(Table.MUSIC_SELECTION_INFO));
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @AutoConnection
    private PreparedStatement prepareForUpdate(PreparedStatement preparedStatement, MusicSelectionInfo object) throws SQLException {

        int i = 0;
        preparedStatement.setLong(++i, object.getPrice());
        preparedStatement.setString(++i, object.getDescription());
        preparedStatement.setString(++i, object.getName());
        return preparedStatement;
    }

    @Override
    @AutoConnection
    protected String getSelectQuery(Table table) {
        return "SELECT * FROM " + table.getValue() + " WHERE ID=?";
    }

    @AutoConnection
    @Override
    public String getInsertQuery() {

        return "INSERT INTO " + Table.MUSIC_SELECTION_INFO.getValue() +
                "(PRICE, DESCRIPTION, NAME) " +
                "VALUES " +
                "(?,?,?)";
    }

    @AutoConnection
    @Override
    public String getUpdateQuery() {

        return "UPDATE " + Table.MUSIC_SELECTION_INFO.getValue() + " SET " +
                "PRICE=?, " +
                "DESCRIPTION=?, " +
                "NAME=? " +
                "WHERE ID=?";
    }
}
