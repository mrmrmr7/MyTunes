package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumDAOTest {
    public static AlbumDAO albumDAO;

    @BeforeAll
    public static void daoInit() {
        albumDAO = new AlbumDAO();
        try {
            albumDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        albumDAO.closeConnection();
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

        Optional<Album> album = albumDAO.getByPK(1);

        String actual = album.get().toString();
        String expected = "Album{id=1, description='Back side of moon', price=123, author_id=1, genre_id=1}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<Album> albumList = albumDAO.getAll();

        String actual = albumList.toString();
        String expected = "[" +
                "Album{id=1, description='Back side of moon', price=123, author_id=1, genre_id=1}, " +
                "Album{id=2, description='Gorgorod', price=123, author_id=2, genre_id=2}, " +
                "Album{id=3, description='Downtown', price=123, author_id=3, genre_id=1}, " +
                "Album{id=4, description='Some1', price=123, author_id=2, genre_id=4}, " +
                "Album{id=5, description='Downtown2', price=123, author_id=5, genre_id=1}, " +
                "Album{id=6, description='Back 3oon', price=123, author_id=6, genre_id=1}, " +
                "Album{id=7, description='Gorgorod3', price=123, author_id=7, genre_id=2}, " +
                "Album{id=8, description='Downtown4', price=123, author_id=8, genre_id=1}, " +
                "Album{id=9, description='Downtown5', price=123, author_id=9, genre_id=1}, " +
                "Album{id=10, description='Downtown6', price=123, author_id=1, genre_id=1}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test //FIXME очень странная реакция. Equals говорит что разные
    void insert() throws DAOException {

        Album expected = new Album(
                11,
                4,
                "Some new album",
                2,
                1
        );

        albumDAO.insert(expected);

        Optional<Album> actual = albumDAO.getByPK(11);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DAOException {

        albumDAO.delete(1);

        List<Album> albumList = albumDAO.getAll();

        String actual = albumList.toString();
        String expected = "[" +
                "Album{id=2, description='Gorgorod', price=123, author_id=2, genre_id=2}, " +
                "Album{id=3, description='Downtown', price=123, author_id=3, genre_id=1}, " +
                "Album{id=4, description='Some1', price=123, author_id=2, genre_id=4}, " +
                "Album{id=5, description='Downtown2', price=123, author_id=5, genre_id=1}, " +
                "Album{id=6, description='Back 3oon', price=123, author_id=6, genre_id=1}, " +
                "Album{id=7, description='Gorgorod3', price=123, author_id=7, genre_id=2}, " +
                "Album{id=8, description='Downtown4', price=123, author_id=8, genre_id=1}, " +
                "Album{id=9, description='Downtown5', price=123, author_id=9, genre_id=1}, " +
                "Album{id=10, description='Downtown6', price=123, author_id=1, genre_id=1}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DAOException {

        Album expected = new Album(
                1,
                4,
                "Some new album",
                2,
                1
        );

        albumDAO.update(expected);

        Optional<Album> actual = albumDAO.getByPK(1);

        assertEquals(expected, actual.get());
    }
}