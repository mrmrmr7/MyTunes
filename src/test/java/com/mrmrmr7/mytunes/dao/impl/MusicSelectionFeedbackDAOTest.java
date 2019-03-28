package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.MusicSelectionFeedback;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MusicSelectionFeedbackDAOTest {
    public static MusicSelectionFeedbackDao musicSelectionFeedbackDAO;

    @BeforeAll
    public static void daoInit() {
        musicSelectionFeedbackDAO = new MusicSelectionFeedbackDao();
        try {
            musicSelectionFeedbackDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        musicSelectionFeedbackDAO.closeConnection();
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
        assertNotNull(musicSelectionFeedbackDAO);
    }

    @Test
    void getByPK() throws DaoException {

        Optional<MusicSelectionFeedback> compositionFeedback;
        compositionFeedback = musicSelectionFeedbackDAO.getByPK(4);

        String actual = compositionFeedback.get().getFeedback();
        String expected = "NORM TAKAYA WIBORKA4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<MusicSelectionFeedback> compositionFeedbackList;
        compositionFeedbackList = musicSelectionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "MusicSelectionFeedback{id=1, feedback='NORM TAKAYA WIBORKA1', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=2, feedback='NORM TAKAYA WIBORKA2', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=3, feedback='NORM TAKAYA WIBORKA3', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=4, feedback='NORM TAKAYA WIBORKA4', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=5, feedback='NORM TAKAYA WIBORKA5', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=6, feedback='NORM TAKAYA WIBORKA6', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=7, feedback='NORM TAKAYA WIBORKA7', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=8, feedback='NORM TAKAYA WIBORKA8', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=9, feedback='NORM TAKAYA WIBORKA9', timestamp=2011-11-11 11:11:11.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DaoException {

        MusicSelectionFeedback compositionFeedback = new MusicSelectionFeedback(
                10,
                "Fail song",
                new Timestamp(456456342)
        );

        musicSelectionFeedbackDAO.insert(compositionFeedback);
        Optional<MusicSelectionFeedback> optionalMusicSelectionFeedback = musicSelectionFeedbackDAO.getByPK(10);

        String actual = optionalMusicSelectionFeedback.get().toString();
        String expected = compositionFeedback.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        musicSelectionFeedbackDAO.delete(1);
        List<MusicSelectionFeedback> compositionFeedbackList = musicSelectionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "MusicSelectionFeedback{id=2, feedback='NORM TAKAYA WIBORKA2', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=3, feedback='NORM TAKAYA WIBORKA3', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=4, feedback='NORM TAKAYA WIBORKA4', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=5, feedback='NORM TAKAYA WIBORKA5', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=6, feedback='NORM TAKAYA WIBORKA6', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=7, feedback='NORM TAKAYA WIBORKA7', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=8, feedback='NORM TAKAYA WIBORKA8', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=9, feedback='NORM TAKAYA WIBORKA9', timestamp=2011-11-11 11:11:11.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DaoException {

        MusicSelectionFeedback compositionFeedback = new MusicSelectionFeedback(
                5,
                "Fail song",
                new Timestamp(1231435)
        );

        musicSelectionFeedbackDAO.update(compositionFeedback);

        Optional<MusicSelectionFeedback> compositionFeedback1 = musicSelectionFeedbackDAO.getByPK(5);

        String actual = compositionFeedback1.get().toString();
        String expected = "MusicSelectionFeedback{id=5, feedback='Fail song', timestamp=1970-01-01 03:20:31.435}";

        assertEquals(expected, actual);
    }
}