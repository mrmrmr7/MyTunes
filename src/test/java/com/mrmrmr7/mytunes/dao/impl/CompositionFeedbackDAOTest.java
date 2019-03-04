package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.After;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompositionFeedbackDAOTest {
    public static CompositionFeedbackDAO compositionFeedbackDAO;

    @BeforeAll
    public static void daoInit() {
        compositionFeedbackDAO = new CompositionFeedbackDAO();
    }

    @AfterAll
    public static void daoDestroy() {
        compositionFeedbackDAO.destroy();
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
        assertNotNull(compositionFeedbackDAO);
    }

    @Test
    void getByPK() throws DAOException {

        Optional<CompositionFeedback> compositionFeedback;
        compositionFeedback = compositionFeedbackDAO.getByPK(4);

        String actual = compositionFeedback.get().getFeedback();
        String expected = "Norm takaya pesnya4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<CompositionFeedback> compositionFeedbackList;
        compositionFeedbackList = compositionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "CompositionFeedback{id=1, feedback='Norm takaya pesnya1', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=2, feedback='Norm takaya pesnya2', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=3, feedback='Norm takaya pesnya3', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=4, feedback='Norm takaya pesnya4', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=5, feedback='Norm takaya pesnya5', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=6, feedback='Norm takaya pesnya6', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=7, feedback='Norm takaya pesnya7', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=8, feedback='Norm takaya pesnya8', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=9, feedback='Norm takaya pesnya9', timestamp=2009-06-04 19:14:20.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DAOException {

        CompositionFeedback compositionFeedback = new CompositionFeedback(
                10,
                "Fail song",
                new Timestamp(456456342)
        );

        compositionFeedbackDAO.insert(compositionFeedback);
        Optional<CompositionFeedback> optionalCompositionFeedback = compositionFeedbackDAO.getByPK(10);

        String actual = optionalCompositionFeedback.get().toString();
        String expected = compositionFeedback.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DAOException {

        compositionFeedbackDAO.delete(1);
        List<CompositionFeedback> compositionFeedbackList = compositionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "CompositionFeedback{id=2, feedback='Norm takaya pesnya2', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=3, feedback='Norm takaya pesnya3', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=4, feedback='Norm takaya pesnya4', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=5, feedback='Norm takaya pesnya5', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=6, feedback='Norm takaya pesnya6', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=7, feedback='Norm takaya pesnya7', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=8, feedback='Norm takaya pesnya8', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=9, feedback='Norm takaya pesnya9', timestamp=2009-06-04 19:14:20.0}]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DAOException {

        CompositionFeedback compositionFeedback = new CompositionFeedback(
                1,
                "Fail song",
                new Timestamp(1231435)
        );

        compositionFeedbackDAO.update(compositionFeedback);

        Optional<CompositionFeedback> compositionFeedback1 = compositionFeedbackDAO.getByPK(1);

        String actual = compositionFeedback1.get().toString();
        String expected = "CompositionFeedback{id=1, feedback='Fail song', timestamp=1970-01-01 03:20:31.435}";

        assertEquals(expected, actual);
    }
}