package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.UserBonus;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserBonusDAOTest {
    public static UserBonusDao userBonusDAO;

    @BeforeAll
    public static void daoInit() {
        userBonusDAO = new UserBonusDao();
    }

    @AfterAll
    public static void daoDestroy() {
        userBonusDAO.closeConnection();
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

        Optional<UserBonus> userBonus = userBonusDAO.getByPK(6);

        String actual = userBonus.get().toString();
        String expected = "UserBonus{id=6, cortageIdList=[9, 10], bonusIdList=[2, 3]}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<UserBonus> userBonusList = userBonusDAO.getAll();

        String actual = userBonusList.toString();
        String expected = "[" +
                "UserBonus{id=1, cortageIdList=[1], bonusIdList=[1]}, " +
                "UserBonus{id=2, cortageIdList=[2], bonusIdList=[2]}, " +
                "UserBonus{id=3, cortageIdList=[3], bonusIdList=[2]}, " +
                "UserBonus{id=4, cortageIdList=[4], bonusIdList=[4]}, " +
                "UserBonus{id=5, cortageIdList=[5], bonusIdList=[5]}, " +
                "UserBonus{id=7, cortageIdList=[6], bonusIdList=[7]}, " +
                "UserBonus{id=8, cortageIdList=[7], bonusIdList=[4]}, " +
                "UserBonus{id=9, cortageIdList=[8], bonusIdList=[9]}, " +
                "UserBonus{id=6, cortageIdList=[9, 10], bonusIdList=[2, 3]}, " +
                "UserBonus{id=10, cortageIdList=[11], bonusIdList=[2]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        UserBonus toInsert = new UserBonus(
                5,
                5,
                4
        );

        userBonusDAO.insert(toInsert);

        Optional<UserBonus> fromInsert = userBonusDAO.getByPK(5);

        String actual = fromInsert.toString();
        String expected = "Optional[UserBonus{id=5, cortageIdList=[5, 12], bonusIdList=[5, 4]}]";

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        userBonusDAO.delete(1);

        List<UserBonus> userBonusList = userBonusDAO.getAll();

        String actual = userBonusList.toString();
        String expected = "[" +
                "UserBonus{id=2, cortageIdList=[2], bonusIdList=[2]}, " +
                "UserBonus{id=3, cortageIdList=[3], bonusIdList=[2]}, " +
                "UserBonus{id=4, cortageIdList=[4], bonusIdList=[4]}, " +
                "UserBonus{id=5, cortageIdList=[5], bonusIdList=[5]}, " +
                "UserBonus{id=7, cortageIdList=[6], bonusIdList=[7]}, " +
                "UserBonus{id=8, cortageIdList=[7], bonusIdList=[4]}, " +
                "UserBonus{id=9, cortageIdList=[8], bonusIdList=[9]}, " +
                "UserBonus{id=6, cortageIdList=[9, 10], bonusIdList=[2, 3]}, " +
                "UserBonus{id=10, cortageIdList=[11], bonusIdList=[2]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        UserBonus userBonus = new UserBonus(
                4,
                4,
                3
        );

        userBonusDAO.update(userBonus);

        String expected = "UserBonus{id=4, cortageIdList=[4], bonusIdList=[3]}";
        String actual = userBonusDAO.getByPK(4).get().toString();

        assertEquals(expected, actual);
    }
}