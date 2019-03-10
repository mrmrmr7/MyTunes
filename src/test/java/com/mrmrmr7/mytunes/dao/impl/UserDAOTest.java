package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    public static UserDao userDAO;

    @BeforeAll
    public static void daoInit() {
        userDAO = new UserDao();
    }

    @AfterAll
    public static void daoDestroy() {
        userDAO.closeConnection();
    }

    @BeforeEach
    void crt() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getInstance() {
        assertNotNull(userDAO);
    }

    @Test
    void getByPK() throws DaoException {

        Optional<User> user1 = userDAO.getByPK(1);

        String expected = "User{ID=1, LOGIN='mrmrmr1', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.s@yandex.by', balance=100, sale=10, role_id=1, status_id=1}";
        String actual = user1.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<User> user = userDAO.getAll();

        String expected = "[" +
                "User{ID=1, LOGIN='mrmrmr1', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.s@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=2, LOGIN='mrmrmr2', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot1@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=3, LOGIN='mrmrmr3', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot2@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=4, LOGIN='mrmrmr4', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot3@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=5, LOGIN='mrmrmr5', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot4@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=6, LOGIN='mrmrmr6', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot5@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=7, LOGIN='mrmrmr7', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot6@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=8, LOGIN='mrmrmr8', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot7@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=9, LOGIN='mrmrmr9', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot8@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=10, LOGIN='mrmrmr10', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot9@yandex.by', balance=100, sale=10, role_id=1, status_id=1}]";
        String actual = user.toString();

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        User user = new User(
                "Kotik",
                "KissMeMeow",
                "Alex",
                "Ermakow",
                "aliex.s@yanedex.kz",
                (byte) 2,
                (byte) 1,
                (byte) 1,
                (byte) 1);

        userDAO.insert(user);

        Optional<User> user1 = userDAO.getByPK(11);

        String expected = "User{ID=11, LOGIN='Kotsik', register_data=1970-01-01, password='KissMeMeow', first_name='Alex', second_name='Ermakow', email='aliex.s@yanedex.kz', balance=2, sale=1, role_id=1, status_id=1}";
        String actual = user1.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        userDAO.delete(1);
        List<User> userList = userDAO.getAll();

        String expected = "[" +
                "User{ID=2, LOGIN='mrmrmr2', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot1@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=3, LOGIN='mrmrmr3', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot2@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=4, LOGIN='mrmrmr4', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot3@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=5, LOGIN='mrmrmr5', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot4@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=6, LOGIN='mrmrmr6', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot5@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=7, LOGIN='mrmrmr7', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot6@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=8, LOGIN='mrmrmr8', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot7@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=9, LOGIN='mrmrmr9', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot8@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=10, LOGIN='mrmrmr10', register_data=2008-08-08, password='EpamClass2019', first_name='First name', second_name='Second name', email='aliex.kot9@yandex.by', balance=100, sale=10, role_id=1, status_id=1}]";

        String actual = userList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        userDAO.update(new User(1,
                new Date(123123),
                "Kotik",
                "KissMeMeow",
                "Alex",
                "Ermakow",
                "aliex.s@yanedex.kz",
                (byte) 2,
                (byte) 1,
                (byte) 1,
                (byte) 1));

        Optional<User> userList = userDAO.getByPK(1);

        String expected = "User{ID=1, LOGIN='Kotik', register_data=1970-01-01, password='KissMeMeow', first_name='Alex', second_name='Ermakow', email='aliex.s@yanedex.kz', balance=2, sale=1, role_id=1, status_id=1}";
        String actual = userList.get().toString();

        assertEquals(expected, actual);
    }
}