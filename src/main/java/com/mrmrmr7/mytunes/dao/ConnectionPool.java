package com.mrmrmr7.mytunes.dao;

import com.mrmrmr7.mytunes.dao.exception.DAOException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws DAOException, InterruptedException;
}
