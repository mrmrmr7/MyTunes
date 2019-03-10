package com.mrmrmr7.mytunes.dao.exception;

import java.sql.SQLException;

public class DaoException extends Exception {
    public DaoException(String s, SQLException e) {
        super(s,e);
    }
    public DaoException(String s) {
        super(s);
    }
}
