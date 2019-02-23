package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CompositionDAOTest {
    private static CompositionDAO compositionDAO;

    @BeforeEach
    void init() throws InterruptedException, SQLException, IOException {
        compositionDAO = CompositionDAO.getInstance();
        DBFill.createDB();
        DBFill.fill();
    }

    @Test
    void getInstance() {
        assertNotNull(compositionDAO);
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }


    @Test
    void getByPK() throws InterruptedException, SQLException, IOException, DAOException {

        Optional<Composition> composition = compositionDAO.getByPK(1);
        String expected = "Composition{id=1, year=2018, price=1, album_id=1, name='Vasilisa'}";
        String actual = composition.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws SQLException, IOException, InterruptedException {

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
    void insert() throws InterruptedException, SQLException, IOException, DAOException {

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
    void delete() throws InterruptedException, SQLException, IOException {

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
    void update() throws InterruptedException, SQLException, IOException, DAOException {

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
    void getByPKList() throws SQLException, DAOException {

        List<Integer> integerList = new ArrayList<>();
        IntStream.range(1,5).forEach(integerList::add);

        List<Composition> compositionList = compositionDAO.getByPKList(integerList);

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