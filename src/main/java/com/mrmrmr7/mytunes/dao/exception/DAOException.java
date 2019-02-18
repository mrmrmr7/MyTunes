package com.mrmrmr7.mytunes.dao.exception;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException(String s, SQLException e) {
        super(s,e);
    }
    public DAOException(String s) {
        super(s);
    }
}
