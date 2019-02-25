package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.UserComposition;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCompositionDAOTest {
    public static UserCompositionDAO userCompositionDAO;

    @BeforeAll
    public static void daoInit() {
        userCompositionDAO = new UserCompositionDAO();
        userCompositionDAO.init();
    }

    @AfterAll
    public static void daoDestroy() {
        userCompositionDAO.destroy();
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
    void getByPK() throws SQLException {

        Optional<UserComposition> userComposition = userCompositionDAO.getByPK(3);

        String actual = userComposition.get().toString();
        String expected = "UserComposition{id=3, cortageIdList=[9, 10], compositionIdList=[1, 3]}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws SQLException {

        List<UserComposition> userCompositionList = userCompositionDAO.getAll();

        String actual = userCompositionList.toString();
        String expected = "[" +
                "UserComposition{id=1, cortageIdList=[1, 2, 3, 4, 5], compositionIdList=[1, 2, 3, 4, 5]}, " +
                "UserComposition{id=2, cortageIdList=[6, 7, 8], compositionIdList=[1, 2, 3]}, " +
                "UserComposition{id=3, cortageIdList=[9, 10], compositionIdList=[1, 3]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws SQLException {

        UserComposition toInsert = new UserComposition(
                5,
                4,
                4
        );

        userCompositionDAO.insert(toInsert);
        Optional<UserComposition> fromDB = userCompositionDAO.getByPK(4);

        String actual = fromDB.get().toString();
        String expected = "UserComposition{id=4, cortageIdList=[11], compositionIdList=[4]}";


        assertEquals(expected, actual);
    }

    @Test
    void delete() throws SQLException {

        userCompositionDAO.delete(2);

        List<UserComposition> userCompositionList = userCompositionDAO.getAll();

        String actual = userCompositionList.toString();
        String expected = "[" +
                "UserComposition{id=1, cortageIdList=[1, 2, 3, 4, 5], compositionIdList=[1, 2, 3, 4, 5]}, " +
                "UserComposition{id=3, cortageIdList=[9, 10], compositionIdList=[1, 3]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException {

        Optional<UserComposition> userComposition = userCompositionDAO.getByPK(3);

        userComposition.get().getCompositionIdList().set(0,5);

        userCompositionDAO.update(userComposition.get());

        String expected = "UserComposition{id=3, cortageIdList=[9, 10], compositionIdList=[5, 3]}";
        String actual = userCompositionDAO.getByPK(3).get().toString();

        assertEquals(expected, actual);
    }
}