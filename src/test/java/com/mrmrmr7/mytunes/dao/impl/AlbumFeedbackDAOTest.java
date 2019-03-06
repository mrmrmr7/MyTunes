package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
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
    public static AlbumFeedbackDAO albumFeedbackDAO;

    @BeforeAll
    public static void daoInit() {
        albumFeedbackDAO = new AlbumFeedbackDAO();

        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DAOException e) {
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
    void getByPK() throws DAOException {

        Optional<AlbumFeedback> albumFeedback;
        albumFeedback = albumFeedbackDAO.getByPK(4);

        String actual = albumFeedback.get().getFeedback();
        String expected = "NORM TAKOY ALBUM4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<AlbumFeedback> albumFeedbackList;
        albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=1, feedback='NORM TAKOY ALBUM1', date=2019-03-05}, " +
                "AlbumFeedback{id=2, feedback='NORM TAKOY ALBUM2', date=2019-03-05}, " +
                "AlbumFeedback{id=3, feedback='NORM TAKOY ALBUM3', date=2019-03-05}, " +
                "AlbumFeedback{id=4, feedback='NORM TAKOY ALBUM4', date=2019-03-05}, " +
                "AlbumFeedback{id=5, feedback='NORM TAKOY ALBUM5', date=2019-03-05}, " +
                "AlbumFeedback{id=6, feedback='NORM TAKOY ALBUM6', date=2019-03-05}, " +
                "AlbumFeedback{id=7, feedback='NORM TAKOY ALBUM7', date=2019-03-05}, " +
                "AlbumFeedback{id=8, feedback='NORM TAKOY ALBUM8', date=2019-03-05}, " +
                "AlbumFeedback{id=9, feedback='NORM TAKOY ALBUM9', date=2019-03-05}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DAOException {

        AlbumFeedback albumFeedback = new AlbumFeedback(
                1,
                "Fail song"
        );

        albumFeedbackDAO.insert(albumFeedback);
        Optional<AlbumFeedback> optionalAlbumFeedback = albumFeedbackDAO.getByPK(1);

        String actual = optionalAlbumFeedback.get().toString();
        String expected = albumFeedback.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DAOException {

        albumFeedbackDAO.delete(1);
        List<AlbumFeedback> albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=2, feedback='NORM TAKOY ALBUM2', date=2019-03-05}, " +
                "AlbumFeedback{id=3, feedback='NORM TAKOY ALBUM3', date=2019-03-05}, " +
                "AlbumFeedback{id=4, feedback='NORM TAKOY ALBUM4', date=2019-03-05}, " +
                "AlbumFeedback{id=5, feedback='NORM TAKOY ALBUM5', date=2019-03-05}, " +
                "AlbumFeedback{id=6, feedback='NORM TAKOY ALBUM6', date=2019-03-05}, " +
                "AlbumFeedback{id=7, feedback='NORM TAKOY ALBUM7', date=2019-03-05}, " +
                "AlbumFeedback{id=8, feedback='NORM TAKOY ALBUM8', date=2019-03-05}, " +
                "AlbumFeedback{id=9, feedback='NORM TAKOY ALBUM9', date=2019-03-05}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DAOException {

        AlbumFeedback expected = new AlbumFeedback(
                2,
                "Fail song"
        );

        albumFeedbackDAO.update(expected);

        Optional<AlbumFeedback> actual = albumFeedbackDAO.getByPK(2);

        assertEquals(expected, actual.get());
    }
}