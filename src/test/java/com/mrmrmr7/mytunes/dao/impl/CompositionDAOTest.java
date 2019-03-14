package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CompositionDAOTest {
    private static CompositionDao compositionDAO;

    @BeforeAll
    public static void daoInit() {
        compositionDAO = new CompositionDao();
        try {
            compositionDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    public static void daoDestroy() {
        compositionDAO.closeConnection();
    }

    @BeforeEach
    void init() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getInstance() {
        assertNotNull(compositionDAO);
    }

    @Test
    void getByPK() throws SQLException, DaoException {

        Optional<Composition> composition = compositionDAO.getByPK(1);
        String expected = "Composition{id=1, year=2018, price=1, album_id=1, name='Vasilisa'}";
        String actual = composition.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<Composition> compositionList = compositionDAO.getAll();
        String expected = "[Composition{id=1, year=2018, price=1, album_id=1, name='Vasilisa'}, " +
                "Composition{id=2, year=2018, price=1, album_id=2, name='Vse OK'}, " +
                "Composition{id=3, year=2018, price=1, album_id=3, name='Hype'}, " +
                "Composition{id=4, year=2018, price=1, album_id=4, name='Space'}, " +
                "Composition{id=5, year=2018, price=1, album_id=4, name='Dor'}, " +
                "Composition{id=6, year=2018, price=1, album_id=5, name='Mom'}, " +
                "Composition{id=7, year=2018, price=1, album_id=6, name='Home'}, " +
                "Composition{id=8, year=2018, price=1, album_id=7, name='LSD'}, " +
                "Composition{id=9, year=2018, price=1, album_id=7, name='Fire'}, " +
                "Composition{id=10, year=2018, price=1, album_id=5, name='OLDUST'}]";
        String actual = compositionList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException, DaoException {

        Composition composition = new Composition(
                11,
                4,
                "Зеленоглазые",
                1,
                2018
        );

        compositionDAO.insert(composition);
        Optional<Composition> compositionOptional = compositionDAO.getByPK(11);

        String actual = compositionOptional.get().toString();
        String expected = composition.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        compositionDAO.delete(2);
        List<Composition> compositionList = compositionDAO.getAll();

        String expected = "[Composition{id=1, year=2018, price=1, album_id=1, name='Vasilisa'}, " +
                "Composition{id=3, year=2018, price=1, album_id=3, name='Hype'}, " +
                "Composition{id=4, year=2018, price=1, album_id=4, name='Space'}, " +
                "Composition{id=5, year=2018, price=1, album_id=4, name='Dor'}, " +
                "Composition{id=6, year=2018, price=1, album_id=5, name='Mom'}, " +
                "Composition{id=7, year=2018, price=1, album_id=6, name='Home'}, " +
                "Composition{id=8, year=2018, price=1, album_id=7, name='LSD'}, " +
                "Composition{id=9, year=2018, price=1, album_id=7, name='Fire'}, " +
                "Composition{id=10, year=2018, price=1, album_id=5, name='OLDUST'}]";

        String actual = compositionList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DaoException {

        Composition composition = new Composition(
                1,
                4,
                "Зеленоглазые",
                1,
                2018);

        compositionDAO.update(composition);
        Optional<Composition> compositionOptional = compositionDAO.getByPK(1);
        String actual = compositionOptional.get().toString();
        String expected = composition.toString();

        assertEquals(expected, actual);
    }

    @Test
    void getByPKList() throws SQLException, DaoException {

        List<Integer> integerList = new ArrayList<>();
        IntStream.range(1,5).forEach(integerList::add);

        List<Composition> compositionList = compositionDAO.getListByPK(integerList);

        String actual = compositionList.toString();
        String expected = "[" +
                "Composition{id=1, year=2018, price=1, album_id=1, name='Vasilisa'}, " +
                "Composition{id=2, year=2018, price=1, album_id=2, name='Vse OK'}, " +
                "Composition{id=3, year=2018, price=1, album_id=3, name='Hype'}, " +
                "Composition{id=4, year=2018, price=1, album_id=4, name='Space'}" +
                "]";

        assertEquals(expected, actual);
    }
}