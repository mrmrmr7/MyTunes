package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Role;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleDAOTest {
    public static RoleDao roleDAO;

    @BeforeAll
    public static void daoInit() {
        roleDAO = new RoleDao();
    }

    @AfterAll
    public static void daoDestroy() {
        roleDAO.closeConnection();
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

        Optional<Role> role = roleDAO.getByPK((byte)1);

        String actual = role.get().toString();
        String expected = "Role{id=1, role='administrator'}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DaoException {

        List<Role> roleList = roleDAO.getAll();

        String actual = roleList.toString();
        String expected = "[" +
                "Role{id=1, role='administrator'}, " +
                "Role{id=2, role='client'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DaoException {

        Role expected = new Role(
                (byte)3,
                "Some new role"
        );

        roleDAO.insert(expected);

        Optional<Role> actual = roleDAO.getByPK((byte)3);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DaoException {

        roleDAO.delete((byte)1);

        List<Role> roleList = roleDAO.getAll();

        String actual = roleList.toString();
        String expected = "[" +
                "Role{id=2, role='client'}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws DaoException {

        Role expected = new Role(
                (byte)1,
                "new role"
        );

        roleDAO.update(expected);

        Optional<Role> actual = roleDAO.getByPK((byte)1);

        assertEquals(expected, actual.get());
    }
}