package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceSignIn;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceSignInImplTest {

    @BeforeEach
    public void init() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }
    @Test
    void executeTest() throws ServiceException {
        for (int i = 0; i < 10; i++) {
            System.out.println(BCrypt.hashpw("SESSION", BCrypt.gensalt()));
        }
        assertTrue(true);
    }
}