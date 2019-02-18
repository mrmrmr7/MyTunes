package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.exception.PersistException;

import java.io.Serializable;
import java.util.Optional;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T extends Identified<PK>, PK extends Serializable> {
    Optional<T> getByPK(PK id) throws DAOException,SQLException;

    List<T> getAll() throws SQLException, DAOException;

    void insert(T daoObject) throws SQLException;

    void delete(PK id) throws SQLException, PersistException;

    void update(T daoObject) throws SQLException, PersistException;
}