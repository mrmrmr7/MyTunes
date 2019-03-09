package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
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
    void getByPK() throws DAOException {

        Optional<MusicSelectionFeedback> compositionFeedback;
        compositionFeedback = musicSelectionFeedbackDAO.getByPK(4);

        String actual = compositionFeedback.get().getFeedback();
        String expected = "Norm takaya wiborka4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<MusicSelectionFeedback> compositionFeedbackList;
        compositionFeedbackList = musicSelectionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "MusicSelectionFeedback{id=1, feedback='Norm takaya wiborka1', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=2, feedback='Norm takaya wiborka2', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=3, feedback='Norm takaya wiborka3', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=4, feedback='Norm takaya wiborka4', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=5, feedback='Norm takaya wiborka5', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=6, feedback='Norm takaya wiborka6', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=7, feedback='Norm takaya wiborka7', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=8, feedback='Norm takaya wiborka8', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=9, feedback='Norm takaya wiborka9', timestamp=2011-11-11 11:11:11.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DAOException {

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
    void delete() throws DAOException {

        musicSelectionFeedbackDAO.delete(1);
        List<MusicSelectionFeedback> compositionFeedbackList = musicSelectionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "MusicSelectionFeedback{id=2, feedback='Norm takaya wiborka2', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=3, feedback='Norm takaya wiborka3', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=4, feedback='Norm takaya wiborka4', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=5, feedback='Norm takaya wiborka5', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=6, feedback='Norm takaya wiborka6', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=7, feedback='Norm takaya wiborka7', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=8, feedback='Norm takaya wiborka8', timestamp=2011-11-11 11:11:11.0}, " +
                "MusicSelectionFeedback{id=9, feedback='Norm takaya wiborka9', timestamp=2011-11-11 11:11:11.0}]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DAOException {

        MusicSelectionFeedback compositionFeedback = new MusicSelectionFeedback(
                1,
                "Fail song",
                new Timestamp(1231435)
        );

        musicSelectionFeedbackDAO.update(compositionFeedback);

        Optional<MusicSelectionFeedback> compositionFeedback1 = musicSelectionFeedbackDAO.getByPK(1);

        String actual = compositionFeedback1.get().toString();
        String expected = "MusicSelectionFeedback{id=1, feedback='Fail song', timestamp=1970-01-01 03:20:31.435}";

        assertEquals(expected, actual);
    }
}