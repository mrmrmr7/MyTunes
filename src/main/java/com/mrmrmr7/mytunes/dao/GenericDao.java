package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.exception.PersistException;

import java.io.Serializable;
import java.util.Optional;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
    Optional<T> getByPK(PK id) throws DAOException;

    List<T> getAll() throws DAOException;

    void insert(T daoObject) throws DAOException;

    void delete(PK id) throws DAOException;

    void update(T daoObject) throws DAOException;
}