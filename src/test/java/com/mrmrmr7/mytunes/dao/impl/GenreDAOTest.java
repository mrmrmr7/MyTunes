package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreDAOTest {
    public static GenreDAO genreDAO;

    @BeforeAll
    public static void daoInit() {
        genreDAO = new GenreDAO();
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
    void getByPK() throws DAOException {

        Optional<Genre> genre = genreDAO.getByPK(1);

        String actual = genre.get().toString();
        String expected = "Genre{id=1, genre='Rock'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<Genre> genreList = genreDAO.getAll();

        String actual = genreList.toString();
        String expected = "[" +
                "Genre{id=1, genre='Rock'}, " +
                "Genre{id=2, genre='Hard Rock'}, " +
                "Genre{id=3, genre='Dubstep'}, " +
                "Genre{id=4, genre='Rap'}, " +
                "Genre{id=5, genre='HipHop'}, " +
                "Genre{id=6, genre='Squad'}, " +
                "Genre{id=7, genre='Pop'}, " +
                "Genre{id=8, genre='K-pop'}, " +
                "Genre{id=9, genre='DJ'}, " +
                "Genre{id=10, genre='Metall'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DAOException {

        Genre expected = new Genre(
                11,
                "Some new genre"
        );

        genreDAO.insert(expected);

        Optional<Genre> actual = genreDAO.getByPK(11);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DAOException {

        genreDAO.delete(1);

        List<Genre> genreList = genreDAO.getAll();

        String actual = genreList.toString();
        String expected = "[" +
                "Genre{id=2, genre='Hard Rock'}, " +
                "Genre{id=3, genre='Dubstep'}, " +
                "Genre{id=4, genre='Rap'}, " +
                "Genre{id=5, genre='HipHop'}, " +
                "Genre{id=6, genre='Squad'}, " +
                "Genre{id=7, genre='Pop'}, " +
                "Genre{id=8, genre='K-pop'}, " +
                "Genre{id=9, genre='DJ'}, " +
                "Genre{id=10, genre='Metall'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DAOException {

        Genre expected = new Genre(
                1,
                "new genre"
        );

        genreDAO.update(expected);

        Optional<Genre> actual = genreDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}