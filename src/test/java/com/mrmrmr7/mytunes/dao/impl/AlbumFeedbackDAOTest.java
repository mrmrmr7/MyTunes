package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlbumFeedbackDAOTest {
    public static AlbumFeedbackDao albumFeedbackDAO;

    @BeforeAll
    public static void daoInit() {
        albumFeedbackDAO = new AlbumFeedbackDao();

        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        albumFeedbackDAO.closeConnection();
    }

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
    void getInstance() {
        assertNotNull(albumFeedbackDAO);
    }

    @Test
    void getByPK() throws DaoException {

        Optional<AlbumFeedback> albumFeedback;
        albumFeedback = albumFeedbackDAO.getByPK(4);

        String actual = albumFeedback.get().getFeedback();
        String expected = "NORM TAKOY ALBUM4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<AlbumFeedback> albumFeedbackList;
        albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=2, feedback='NORM TAKOY ALBUM2', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=3, feedback='NORM TAKOY ALBUM3', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=4, feedback='NORM TAKOY ALBUM4', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=5, feedback='NORM TAKOY ALBUM5', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=6, feedback='NORM TAKOY ALBUM6', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=7, feedback='NORM TAKOY ALBUM7', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=8, feedback='NORM TAKOY ALBUM8', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=9, feedback='NORM TAKOY ALBUM9', timestamp=2019-05-03 12:00:03.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DaoException {

        AlbumFeedback albumFeedback = new AlbumFeedback(
                11,
                "Fail song",
                new Timestamp(123223)
        );

        albumFeedbackDAO.insert(albumFeedback);
        Optional<AlbumFeedback> optionalAlbumFeedback = albumFeedbackDAO.getByPK(11);

        String actual = optionalAlbumFeedback.get().toString();
        String expected = albumFeedback.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        albumFeedbackDAO.delete(1);
        List<AlbumFeedback> albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=2, feedback='NORM TAKOY ALBUM2', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=3, feedback='NORM TAKOY ALBUM3', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=4, feedback='NORM TAKOY ALBUM4', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=5, feedback='NORM TAKOY ALBUM5', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=6, feedback='NORM TAKOY ALBUM6', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=7, feedback='NORM TAKOY ALBUM7', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=8, feedback='NORM TAKOY ALBUM8', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=9, feedback='NORM TAKOY ALBUM9', timestamp=2019-05-03 12:00:03.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        AlbumFeedback expected = new AlbumFeedback(
                2,
                "Fail song",
                new Timestamp(12343234)
        );

        albumFeedbackDAO.update(expected);

        Optional<AlbumFeedback> actual = albumFeedbackDAO.getByPK(2);

        assertEquals(expected, actual.get());
    }
}