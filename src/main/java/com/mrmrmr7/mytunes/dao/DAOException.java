package com.mrmrmr7.mytunes.dao;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException(String s, SQLException e) {
        super(s,e);
    }
}
