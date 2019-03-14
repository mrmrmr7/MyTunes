package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreDAOTest {
    public static GenreDao genreDAO;

    @BeforeAll
    public static void daoInit() {
        genreDAO = new GenreDao();
        try {
            genreDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        genreDAO.closeConnection();
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

        Optional<Genre> genre = genreDAO.getByPK(1);

        String actual = genre.get().toString();
        String expected = "Genre{id=1, genre='ROCK'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<Genre> genreList = genreDAO.getAll();

        String actual = genreList.toString();
        String expected = "[" +
                "Genre{id=1, genre='ROCK'}, " +
                "Genre{id=2, genre='HARD ROCK'}, " +
                "Genre{id=3, genre='DUBSTEP'}, " +
                "Genre{id=4, genre='RAP'}, " +
                "Genre{id=5, genre='HIPHOP'}, " +
                "Genre{id=6, genre='SQUAD'}, " +
                "Genre{id=7, genre='POP'}, " +
                "Genre{id=8, genre='K-POP'}, " +
                "Genre{id=9, genre='DJ'}, " +
                "Genre{id=10, genre='METALL'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        Genre expected = new Genre(
                11,
                "Some new genre"
        );

        genreDAO.insert(expected);

        Optional<Genre> actual = genreDAO.getByPK(11);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DaoException {

        genreDAO.delete(1);

        List<Genre> genreList = genreDAO.getAll();

        String actual = genreList.toString();
        String expected = "[" +
                "Genre{id=2, genre='HARD ROCK'}, " +
                "Genre{id=3, genre='DUBSTEP'}, " +
                "Genre{id=4, genre='RAP'}, " +
                "Genre{id=5, genre='HIPHOP'}, " +
                "Genre{id=6, genre='SQUAD'}, " +
                "Genre{id=7, genre='POP'}, " +
                "Genre{id=8, genre='K-POP'}, " +
                "Genre{id=9, genre='DJ'}, " +
                "Genre{id=10, genre='METALL'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Genre expected = new Genre(
                1,
                "new genre"
        );

        genreDAO.update(expected);

        Optional<Genre> actual = genreDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}