package com.mrmrmr7.mytunes.dao.impl;

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
        albumFeedbackDAO.init();
    }

    @AfterAll
    public static void daoDestroy() {
        albumFeedbackDAO.destroy();
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
    void getByPK() throws SQLException {

        Optional<AlbumFeedback> albumFeedback;
        albumFeedback = albumFeedbackDAO.getByPK(4);

        String actual = albumFeedback.get().getFeedback();
        String expected = "Norm takoy album4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws SQLException {

        List<AlbumFeedback> albumFeedbackList;
        albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=1, feedback='Norm takoy album1', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=2, feedback='Norm takoy album2', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=3, feedback='Norm takoy album3', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=4, feedback='Norm takoy album4', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=5, feedback='Norm takoy album5', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=6, feedback='Norm takoy album6', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=7, feedback='Norm takoy album7', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=8, feedback='Norm takoy album8', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=9, feedback='Norm takoy album9', timestamp=2019-05-03 12:00:03.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DAOException {

        AlbumFeedback albumFeedback = new AlbumFeedback(
                10,
                "Fail song",
                new Timestamp(456456342)
        );

        albumFeedbackDAO.insert(albumFeedback);
        Optional<AlbumFeedback> optionalAlbumFeedback = albumFeedbackDAO.getByPK(10);

        String actual = optionalAlbumFeedback.get().toString();
        String expected = albumFeedback.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws SQLException {

        albumFeedbackDAO.delete(1);
        List<AlbumFeedback> albumFeedbackList = albumFeedbackDAO.getAll();

        String actual = albumFeedbackList.toString();
        String expected = "[" +
                "AlbumFeedback{id=2, feedback='Norm takoy album2', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=3, feedback='Norm takoy album3', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=4, feedback='Norm takoy album4', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=5, feedback='Norm takoy album5', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=6, feedback='Norm takoy album6', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=7, feedback='Norm takoy album7', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=8, feedback='Norm takoy album8', timestamp=2019-05-03 12:00:03.0}, " +
                "AlbumFeedback{id=9, feedback='Norm takoy album9', timestamp=2019-05-03 12:00:03.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DAOException {

        AlbumFeedback albumFeedback = new AlbumFeedback(
                1,
                "Fail song",
                new Timestamp(1231435)
        );

        albumFeedbackDAO.update(albumFeedback);

        Optional<AlbumFeedback> albumFeedback1 = albumFeedbackDAO.getByPK(1);

        String actual = albumFeedback1.get().toString();
        String expected = "AlbumFeedback{id=1, feedback='Fail song', timestamp=1970-01-01 03:20:31.435}";

        assertEquals(expected, actual);
    }
}