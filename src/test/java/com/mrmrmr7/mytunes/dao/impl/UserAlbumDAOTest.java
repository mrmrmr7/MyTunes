package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserAlbum;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAlbumDAOTest {
    public static UserAlbumDao userAlbumDAO;

    @BeforeAll
    public static void daoInit() {
        userAlbumDAO = new UserAlbumDao();
        try {
            userAlbumDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        userAlbumDAO.closeConnection();
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

        Optional<UserAlbum> userAlbum = userAlbumDAO.getByPK(6);

        String actual = userAlbum.get().toString();
        String expected = "UserAlbum{id=6, cortageIdList=[8], albumIdList=[5]}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<UserAlbum> userAlbumList = userAlbumDAO.getAll();

        String actual = userAlbumList.toString();
        String expected = "[" +
                "UserAlbum{id=1, cortageIdList=[1, 2, 3], albumIdList=[3, 5, 4]}, " +
                "UserAlbum{id=2, cortageIdList=[4], albumIdList=[3]}, " +
                "UserAlbum{id=3, cortageIdList=[5], albumIdList=[4]}, " +
                "UserAlbum{id=4, cortageIdList=[6], albumIdList=[1]}, " +
                "UserAlbum{id=5, cortageIdList=[7], albumIdList=[2]}, " +
                "UserAlbum{id=6, cortageIdList=[8], albumIdList=[5]}, " +
                "UserAlbum{id=7, cortageIdList=[9], albumIdList=[8]}, " +
                "UserAlbum{id=8, cortageIdList=[10], albumIdList=[3]}, " +
                "UserAlbum{id=9, cortageIdList=[11, 12], albumIdList=[4, 2]}, " +
                "UserAlbum{id=10, cortageIdList=[13], albumIdList=[1]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        UserAlbum toInsert = new UserAlbum(
                5,
                6,
                4
        );

        userAlbumDAO.insert(toInsert);
        Optional<UserAlbum> fromDB = userAlbumDAO.getByPK(6);

        String actual = fromDB.get().toString();
        String expected = "UserAlbum{id=6, cortageIdList=[8, 14], albumIdList=[5, 4]}";


        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        userAlbumDAO.delete(2);

        List<UserAlbum> userAlbumList = userAlbumDAO.getAll();

        String actual = userAlbumList.toString();
        String expected = "[" +
                "UserAlbum{id=1, cortageIdList=[1, 2, 3], albumIdList=[3, 5, 4]}, " +
                "UserAlbum{id=3, cortageIdList=[5], albumIdList=[4]}, " +
                "UserAlbum{id=4, cortageIdList=[6], albumIdList=[1]}, " +
                "UserAlbum{id=5, cortageIdList=[7], albumIdList=[2]}, " +
                "UserAlbum{id=6, cortageIdList=[8], albumIdList=[5]}, " +
                "UserAlbum{id=7, cortageIdList=[9], albumIdList=[8]}, " +
                "UserAlbum{id=8, cortageIdList=[10], albumIdList=[3]}, " +
                "UserAlbum{id=9, cortageIdList=[11, 12], albumIdList=[4, 2]}, " +
                "UserAlbum{id=10, cortageIdList=[13], albumIdList=[1]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Optional<UserAlbum> userAlbum = userAlbumDAO.getByPK(4);

        userAlbum.get().getAlbumIdList().set(0,5);

        userAlbumDAO.update(userAlbum.get());

        String expected = "UserAlbum{id=4, cortageIdList=[6], albumIdList=[5]}";
        String actual = userAlbumDAO.getByPK(4).get().toString();

        assertEquals(expected, actual);
    }
}