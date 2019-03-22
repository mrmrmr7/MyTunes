package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
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
        try {
            userDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
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

        String expected = "User{ID=1, LOGIN='MRMRMR11', register_data=2008-08-08, password='$2a$10$PyrB3GSKF5wo2zXpTh1SC.GLx7c7f92oy3rxzAmYLeAOriYIzuIy.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot1@yandex.by', balance=100, sale=10, role_id=1, status_id=1}";
        String actual = user1.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<User> user = userDAO.getAll();

        String expected = "[" +
                "User{ID=1, LOGIN='MRMRMR11', register_data=2008-08-08, password='$2a$10$PyrB3GSKF5wo2zXpTh1SC.GLx7c7f92oy3rxzAmYLeAOriYIzuIy.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot1@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=2, LOGIN='MRMRMR12', register_data=2008-08-08, password='$2a$10$mlAlOP7Qwq1nEMRdqeU87OjWKjmV7/cxHTdeeJT5OxFBB4eHJ0uGS', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot2@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=3, LOGIN='MRMRMR13', register_data=2008-08-08, password='$2a$10$tfIFXPz6VxdRi5KOGxB9runD0sHVI2dSmrOCvCzcZRZoXH.hZBL8.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot3@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=4, LOGIN='MRMRMR14', register_data=2008-08-08, password='$2a$10$YCsHtEdLw2djrLQOzhUP.eFzjnFyeyr.9dDr2ePQ.j8gP3OEgH0Uy', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot4@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=5, LOGIN='MRMRMR15', register_data=2008-08-08, password='$2a$10$fxOSMDJhcZc7xEBkAjfmG.Dq1rV04h0qTS4UpoxWrvEDOa8UyFsvG', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot5@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=6, LOGIN='MRMRMR16', register_data=2008-08-08, password='$2a$10$SiA79Eiouun12HvmtoYf7u6c8j/mx/SFHlNm0NOMmVkYI7RV9X5gu', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot6@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=7, LOGIN='MRMRMR17', register_data=2008-08-08, password='$2a$10$kVtSXe2CxB/LxzxFIi4d7OpaTtmv.OjNke9v38NtAI8MgfjiEAnT.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot7@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=8, LOGIN='MRMRMR18', register_data=2008-08-08, password='$2a$10$Xef0CHEFBJnLaYWrYl2RE.8zGBJqK4pCKxAwirM3ku28vPxvecIFu', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot8@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=9, LOGIN='MRMRMR19', register_data=2008-08-08, password='$2a$10$FdNnLot6QA2mIj/wWFR6uu4a.Fvtrf3Y9VXdIMUOfLq9R5EUFFjxS', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot9@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=10, LOGIN='MRMRMR10', register_data=2008-08-08, password='$2a$10$nJzOa80/gZFdfBjEsgHlq.DlCAuV9gtluYPTkf9bs8XZj2EiIBm1G', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot10@yandex.by', balance=100, sale=10, role_id=1, status_id=1}" +
                "]";
        String actual = user.toString();

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        User user = new User(
                2,
                new Date(324234234),
                "Kotik",
                "KissMeMeow",
                "Alex",
                "Ermakow",
                "aliex.s@yanedex.kz",
                (long) 2,
                (byte) 1,
                (byte) 1,
                (byte) 1,
                "agfdghdsdfasfasf");

        userDAO.insert(user);

        Optional<User> user1 = userDAO.getByPK(11);


        String expected = "User{ID=11, LOGIN='Kotik', register_data=1970-01-04, password='KissMeMeow', first_name='Alex', second_name='Ermakow', email='aliex.s@yanedex.kz', balance=2, sale=1, role_id=1, status_id=1}";
        String actual = user1.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    void delete() throws DaoException {

        userDAO.delete(1);
        List<User> userList = userDAO.getAll();

        String expected = "[" +
                "User{ID=2, LOGIN='MRMRMR12', register_data=2008-08-08, password='$2a$10$mlAlOP7Qwq1nEMRdqeU87OjWKjmV7/cxHTdeeJT5OxFBB4eHJ0uGS', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot2@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=3, LOGIN='MRMRMR13', register_data=2008-08-08, password='$2a$10$tfIFXPz6VxdRi5KOGxB9runD0sHVI2dSmrOCvCzcZRZoXH.hZBL8.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot3@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=4, LOGIN='MRMRMR14', register_data=2008-08-08, password='$2a$10$YCsHtEdLw2djrLQOzhUP.eFzjnFyeyr.9dDr2ePQ.j8gP3OEgH0Uy', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot4@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=5, LOGIN='MRMRMR15', register_data=2008-08-08, password='$2a$10$fxOSMDJhcZc7xEBkAjfmG.Dq1rV04h0qTS4UpoxWrvEDOa8UyFsvG', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot5@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=6, LOGIN='MRMRMR16', register_data=2008-08-08, password='$2a$10$SiA79Eiouun12HvmtoYf7u6c8j/mx/SFHlNm0NOMmVkYI7RV9X5gu', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot6@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=7, LOGIN='MRMRMR17', register_data=2008-08-08, password='$2a$10$kVtSXe2CxB/LxzxFIi4d7OpaTtmv.OjNke9v38NtAI8MgfjiEAnT.', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot7@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=8, LOGIN='MRMRMR18', register_data=2008-08-08, password='$2a$10$Xef0CHEFBJnLaYWrYl2RE.8zGBJqK4pCKxAwirM3ku28vPxvecIFu', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot8@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=9, LOGIN='MRMRMR19', register_data=2008-08-08, password='$2a$10$FdNnLot6QA2mIj/wWFR6uu4a.Fvtrf3Y9VXdIMUOfLq9R5EUFFjxS', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot9@yandex.by', balance=100, sale=10, role_id=1, status_id=1}, " +
                "User{ID=10, LOGIN='MRMRMR10', register_data=2008-08-08, password='$2a$10$nJzOa80/gZFdfBjEsgHlq.DlCAuV9gtluYPTkf9bs8XZj2EiIBm1G', first_name='FIRST NAME', second_name='SECOND NAME', email='aliex.kot10@yandex.by', balance=100, sale=10, role_id=1, status_id=1}" +
                "]";
        String actual = userList.toString();

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        userDAO.update(new User(
                2,
                new Date(324234234),
                "Kotik",
                "KissMeMeow",
                "Alex",
                "Ermakow",
                "aliex.s@yanedex.kz",
                (long) 2,
                (byte) 1,
                (byte) 1,
                (byte) 1,
                "agfdghdsdfasfasf"));

        Optional<User> userList = userDAO.getByPK(2);

        String expected = "User{ID=2, LOGIN='Kotik', register_data=1970-01-04, password='KissMeMeow', first_name='Alex', second_name='Ermakow', email='aliex.s@yanedex.kz', balance=2, sale=1, role_id=1, status_id=1}";
        String actual = userList.get().toString();

        assertEquals(expected, actual);
    }
}