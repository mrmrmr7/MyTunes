package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompositionFeedbackDAOTest {
    public static CompositionFeedbackDao compositionFeedbackDAO;

    @BeforeAll
    public static void daoInit() {
        compositionFeedbackDAO = new CompositionFeedbackDao();
        try {
            compositionFeedbackDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        compositionFeedbackDAO.closeConnection();
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
    void getByPK() throws DaoException {

        Optional<CompositionFeedback> compositionFeedback;
        compositionFeedback = compositionFeedbackDAO.getByPK(4);

        String actual = compositionFeedback.get().getFeedback();
        String expected = "NORM TAKAYA PESNYA4";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<CompositionFeedback> compositionFeedbackList;
        compositionFeedbackList = compositionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "CompositionFeedback{id=1, feedback='NORM TAKAYA PESNYA1', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=2, feedback='NORM TAKAYA PESNYA2', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=3, feedback='NORM TAKAYA PESNYA3', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=4, feedback='NORM TAKAYA PESNYA4', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=5, feedback='NORM TAKAYA PESNYA5', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=6, feedback='NORM TAKAYA PESNYA6', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=7, feedback='NORM TAKAYA PESNYA7', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=8, feedback='NORM TAKAYA PESNYA8', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=9, feedback='NORM TAKAYA PESNYA9', timestamp=2009-06-04 19:14:20.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DaoException {

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
    void delete() throws DaoException {

        compositionFeedbackDAO.delete(1);
        List<CompositionFeedback> compositionFeedbackList = compositionFeedbackDAO.getAll();

        String actual = compositionFeedbackList.toString();
        String expected = "[" +
                "CompositionFeedback{id=2, feedback='NORM TAKAYA PESNYA2', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=3, feedback='NORM TAKAYA PESNYA3', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=4, feedback='NORM TAKAYA PESNYA4', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=5, feedback='NORM TAKAYA PESNYA5', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=6, feedback='NORM TAKAYA PESNYA6', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=7, feedback='NORM TAKAYA PESNYA7', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=8, feedback='NORM TAKAYA PESNYA8', timestamp=2009-06-04 19:14:20.0}, " +
                "CompositionFeedback{id=9, feedback='NORM TAKAYA PESNYA9', timestamp=2009-06-04 19:14:20.0}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DaoException {

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