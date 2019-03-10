package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DaoException;

import java.io.Serializable;
import java.util.Optional;

import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    @AutoConnection
    Optional<T> getByPK(PK id) throws DaoException;

    @AutoConnection
    List<T> getAll() throws DaoException;

    @AutoConnection
    void insert(T daoObject) throws DaoException;

    @AutoConnection
    void delete(PK id) throws DaoException;

    @AutoConnection
    void update(T daoObject) throws DaoException;
}