package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.SessionData;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionDataDAOTest {
    public static SessionDataDao sessionDataDAO;

    @BeforeAll
    public static void daoInit() {
        sessionDataDAO = new SessionDataDao();
    }

    @AfterAll
    public static void daoDestroy() {
        sessionDataDAO.closeConnection();
    }

    @BeforeEach
    public void init() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    public void destr() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getByPK() throws DAOException {

        Optional<SessionData> sessionData = sessionDataDAO.getByPK(1);

        String actual = sessionData.get().toString();
        String expected = "SessionData{id=1, sessionData='administrator'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<SessionData> sessionDataList = sessionDataDAO.getAll();

        String actual = sessionDataList.toString();
        String expected = "[" +
                "SessionData{id=1, sessionData='administrator'}, " +
                "SessionData{id=2, sessionData='client'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DAOException {

        SessionData expected = new SessionData(
                3,
                "Some new sessionData".getBytes()
        );

        sessionDataDAO.insert(expected);

        Optional<SessionData> actual = sessionDataDAO.getByPK(3);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DAOException {

        int k = 2;
        k *=2 ;
        sessionDataDAO.delete(1);

        List<SessionData> sessionDataList = sessionDataDAO.getAll();

        String actual = sessionDataList.toString();
        String expected = "[" +
                "SessionData{id=2, sessionData='client'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DAOException {

        SessionData expected = new SessionData(
                1,
                "new sessionData".getBytes()
        );

        sessionDataDAO.update(expected);

        Optional<SessionData> actual = sessionDataDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}