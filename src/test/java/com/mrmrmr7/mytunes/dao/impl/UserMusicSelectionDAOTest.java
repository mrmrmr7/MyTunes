package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserMusicSelection;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMusicSelectionDAOTest {
    public static UserMusicSelectionDao userMusicSelectionDAO;

    @BeforeAll
    public static void daoInit() {
        userMusicSelectionDAO = new UserMusicSelectionDao();
        try {
            userMusicSelectionDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        userMusicSelectionDAO.closeConnection();
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

        Optional<UserMusicSelection> userMusicSelection = userMusicSelectionDAO.getByPK(3);

        String actual = userMusicSelection.get().toString();
        String expected = "UserMusicSelection{id=3, cortageIdList=[9, 13], musicSelectionIdList=[8, 8]}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<UserMusicSelection> userMusicSelectionList = userMusicSelectionDAO.getAll();

        String actual = userMusicSelectionList.toString();
        String expected = "[" +
                "UserMusicSelection{id=1, cortageIdList=[1, 11], musicSelectionIdList=[10, 10]}, " +
                "UserMusicSelection{id=2, cortageIdList=[2, 12], musicSelectionIdList=[9, 9]}, " +
                "UserMusicSelection{id=3, cortageIdList=[13, 9], musicSelectionIdList=[8, 8]}, " +
                "UserMusicSelection{id=4, cortageIdList=[3, 14], musicSelectionIdList=[7, 7]}, " +
                "UserMusicSelection{id=5, cortageIdList=[4, 15], musicSelectionIdList=[6, 6]}, " +
                "UserMusicSelection{id=6, cortageIdList=[5, 16], musicSelectionIdList=[5, 5]}, " +
                "UserMusicSelection{id=7, cortageIdList=[17, 6], musicSelectionIdList=[4, 4]}, " +
                "UserMusicSelection{id=8, cortageIdList=[7, 18], musicSelectionIdList=[3, 3]}, " +
                "UserMusicSelection{id=9, cortageIdList=[8, 19], musicSelectionIdList=[2, 2]}, " +
                "UserMusicSelection{id=10, cortageIdList=[10, 20], musicSelectionIdList=[1, 1]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        UserMusicSelection toInsert = new UserMusicSelection(
                5,
                4,
                4
        );

        userMusicSelectionDAO.insert(toInsert);
        Optional<UserMusicSelection> fromDB = userMusicSelectionDAO.getByPK(4);

        String actual = fromDB.get().toString();
        String expected = "UserMusicSelection{id=4, cortageIdList=[3, 14, 21], musicSelectionIdList=[7, 7, 4]}";


        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        userMusicSelectionDAO.delete(2);

        List<UserMusicSelection> userMusicSelectionList = userMusicSelectionDAO.getAll();

        String actual = userMusicSelectionList.toString();
        String expected = "[" +
                "UserMusicSelection{id=1, cortageIdList=[1, 11], musicSelectionIdList=[10, 10]}, " +
                "UserMusicSelection{id=3, cortageIdList=[9, 13], musicSelectionIdList=[8, 8]}, " +
                "UserMusicSelection{id=4, cortageIdList=[3, 14], musicSelectionIdList=[7, 7]}, " +
                "UserMusicSelection{id=5, cortageIdList=[4, 15], musicSelectionIdList=[6, 6]}, " +
                "UserMusicSelection{id=6, cortageIdList=[5, 16], musicSelectionIdList=[5, 5]}, " +
                "UserMusicSelection{id=7, cortageIdList=[6, 17], musicSelectionIdList=[4, 4]}, " +
                "UserMusicSelection{id=8, cortageIdList=[7, 18], musicSelectionIdList=[3, 3]}, " +
                "UserMusicSelection{id=9, cortageIdList=[8, 19], musicSelectionIdList=[2, 2]}, " +
                "UserMusicSelection{id=10, cortageIdList=[10, 20], musicSelectionIdList=[1, 1]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Optional<UserMusicSelection> userMusicSelection = userMusicSelectionDAO.getByPK(3);

        userMusicSelection.get().getMusicSelectionIdList().set(0,5);

        userMusicSelectionDAO.update(userMusicSelection.get());

        String expected = "UserMusicSelection{id=3, cortageIdList=[9, 13], musicSelectionIdList=[5, 8]}";
        String actual = userMusicSelectionDAO.getByPK(3).get().toString();

        assertEquals(expected, actual);
    }
}