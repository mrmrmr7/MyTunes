package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Status;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusDAOTest {
    public static StatusDao statusDAO;

    @BeforeAll
    public static void daoInit() {
        statusDAO = new StatusDao();
        try {
            statusDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        statusDAO.closeConnection();
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
    void getByPK() throws DaoException {

        Optional<Status> status = statusDAO.getByPK((byte)1);

        String actual = status.get().toString();
        String expected = "Status{id=1, status='ACTIVE'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<Status> statusList = statusDAO.getAll();

        String actual = statusList.toString();
        String expected = "[" +
                "Status{id=1, status='ACTIVE'}, " +
                "Status{id=2, status='DEACTIVE'}, " +
                "Status{id=3, status='DELETE'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        Status expected = new Status(
                (byte)4,
                "Some new status"
        );

        statusDAO.insert(expected);

        Optional<Status> actual = statusDAO.getByPK((byte)4);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DaoException {

        statusDAO.delete((byte)1);

        List<Status> statusList = statusDAO.getAll();

        String actual = statusList.toString();
        String expected = "[" +
                "Status{id=2, status='DEACTIVE'}, " +
                "Status{id=3, status='DELETE'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Status expected = new Status(
                (byte)1,
                "new status"
        );

        statusDAO.update(expected);

        Optional<Status> actual = statusDAO.getByPK((byte)1);

        assertEquals(expected, actual.get());
    }
}