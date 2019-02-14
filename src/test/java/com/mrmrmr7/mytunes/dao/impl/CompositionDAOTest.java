package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompositionDAOTest {
    private static CompositionDAO compositionDAO;

    @BeforeEach
    void init() throws InterruptedException, SQLException, IOException {
        compositionDAO = CompositionDAO.getInstance();
        DBFill.createDB();
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
        DBFill.fill();

        Optional<Composition> composition = compositionDAO.getByPK(1);
        String expected = "Composition{id=1, year=2018, price=1, album_id=3, name='Vasilisa'}";
        String actual = composition.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws SQLException, IOException, InterruptedException {
        DBFill.fill();

        List<Composition> compositionList = compositionDAO.getAll();
        String expected = "[Composition{id=1, year=2018, price=1, album_id=3, name='Vasilisa'}]";
        String actual = compositionList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

        Composition composition = new Composition(2,
                4,
                "Зеленоглазые",
                1,
                2018);

        compositionDAO.insert(composition);
        Optional<Composition> compositionOptional = compositionDAO.getByPK(2);

        String actual = compositionOptional.get().toString();
        String expected = composition.toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws InterruptedException, SQLException, IOException {
        DBFill.fill();

        compositionDAO.delete(2);
        List<Composition> compositionList = compositionDAO.getAll();

        String expected = "[Composition{id=1, year=2018, price=1, album_id=3, name='Vasilisa'}]";

        String actual = compositionList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void update() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

        Composition composition = new Composition(1,
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
}