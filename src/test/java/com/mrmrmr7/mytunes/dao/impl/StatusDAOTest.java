package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.entity.Status;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusDAOTest {
    public static StatusDAO statusDAO;

    @BeforeAll
    public static void daoInit() {
        statusDAO = new StatusDAO();
    }

    @AfterAll
    public static void daoDestroy() {
        statusDAO.destroy();
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
    void getByPK() throws SQLException {

        Optional<Status> status = statusDAO.getByPK(1);

        String actual = status.get().toString();
        String expected = "Status{id=1, status='activated'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws SQLException {

        List<Status> statusList = statusDAO.getAll();

        String actual = statusList.toString();
        String expected = "[" +
                "Status{id=1, status='activated'}, " +
                "Status{id=2, status='deactivated'}, " +
                "Status{id=3, status='deleted'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException {

        Status expected = new Status(
                4,
                "Some new status"
        );

        statusDAO.insert(expected);

        Optional<Status> actual = statusDAO.getByPK(4);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws SQLException {

        statusDAO.delete(1);

        List<Status> statusList = statusDAO.getAll();

        String actual = statusList.toString();
        String expected = "[" +
                "Status{id=2, status='deactivated'}, " +
                "Status{id=3, status='deleted'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException {

        Status expected = new Status(
                1,
                "new status"
        );

        statusDAO.update(expected);

        Optional<Status> actual = statusDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}