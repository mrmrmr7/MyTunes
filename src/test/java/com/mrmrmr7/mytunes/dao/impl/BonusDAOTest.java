package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BonusDAOTest {
    public static BonusDao bonusDAO;

    @BeforeAll
    public static void daoInit() {
        bonusDAO = new BonusDao();
    }

    @AfterAll
    public static void daoDestroy() {
        bonusDAO.closeConnection();
    }

    @BeforeEach
    public void init() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    public void destr() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getByPK() throws DaoException {

        Optional<Bonus> bonus = bonusDAO.getByPK(1);

        String actual = bonus.get().toString();
        String expected = "Bonus{id=1, bonus='sale 40%'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<Bonus> bonusList = bonusDAO.getAll();

        String actual = bonusList.toString();
        String expected = "[" +
                "Bonus{id=1, bonus='sale 40%'}, " +
                "Bonus{id=2, bonus='sale 50%'}, " +
                "Bonus{id=3, bonus='sale 60%'}, " +
                "Bonus{id=4, bonus='sale 70%'}, " +
                "Bonus{id=5, bonus='sale 80%'}, " +
                "Bonus{id=6, bonus='sale 90%'}, " +
                "Bonus{id=7, bonus='free song'}, " +
                "Bonus{id=8, bonus='free album'}, " +
                "Bonus{id=9, bonus='free music selection'}, " +
                "Bonus{id=10, bonus='unlim'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        Bonus expected = new Bonus(
                11,
                "Some new bonus"
        );

        bonusDAO.insert(expected);

        Optional<Bonus> actual = bonusDAO.getByPK(11);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DaoException {

        bonusDAO.delete(1);

        List<Bonus> bonusList = bonusDAO.getAll();

        String actual = bonusList.toString();
        String expected = "[" +
                "Bonus{id=2, bonus='sale 50%'}, " +
                "Bonus{id=3, bonus='sale 60%'}, " +
                "Bonus{id=4, bonus='sale 70%'}, " +
                "Bonus{id=5, bonus='sale 80%'}, " +
                "Bonus{id=6, bonus='sale 90%'}, " +
                "Bonus{id=7, bonus='free song'}, " +
                "Bonus{id=8, bonus='free album'}, " +
                "Bonus{id=9, bonus='free music selection'}, " +
                "Bonus{id=10, bonus='unlim'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Bonus expected = new Bonus(
                1,
                "new bonus"
        );

        bonusDAO.update(expected);

        Optional<Bonus> actual = bonusDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}