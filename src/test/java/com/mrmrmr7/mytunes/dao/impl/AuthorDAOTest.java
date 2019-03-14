package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorDAOTest {
    private static AuthorDao authorDAO;

    @BeforeAll
    public static void daoInit() {
        authorDAO = new AuthorDao();
        try {
            authorDAO.setConnection(ConnectionPoolFactory.getInstance().getConnectionPool(ConnectionPoolType.MYSQL).getConnection());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void daoDestroy() {
        authorDAO.closeConnection();
    }

    @BeforeEach
    void init() throws InterruptedException, SQLException, IOException {
        DBFill.createDB();
        DBFill.fill();
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getInstance() {
        assertNotNull(authorDAO);
    }

    @Test
    public void getByPK() throws DaoException {

        Optional<Author> author = authorDAO.getByPK(1);
        String expected = "Author{id=1, firstName='UNKNOWN', secondName='UNKNOWN', pseudonim='UNKNOWN'}";
        String actual = author.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll() throws DaoException {

        List<Author> authorList = authorDAO.getAll();

        String expected = "[" +
                "Author{id=1, firstName='UNKNOWN', secondName='UNKNOWN', pseudonim='UNKNOWN'}, " +
                "Author{id=2, firstName='ALEXANDR', secondName='ZAPOROZHTSEV', pseudonim='GRAND-MASTER'}, " +
                "Author{id=3, firstName='IOSIPH', secondName='STALIN', pseudonim='SLAVA KPSS'}, " +
                "Author{id=4, firstName='LOSKAH', secondName='ANTON', pseudonim='MORGENSTERN'}, " +
                "Author{id=5, firstName='DVERI', secondName='ANON', pseudonim='STONE'}, " +
                "Author{id=6, firstName='SPICE', secondName='SNAF', pseudonim='LSP'}, " +
                "Author{id=7, firstName='ANDREY', secondName='KIRILL', pseudonim='FLOVERS'}, " +
                "Author{id=8, firstName='MALOY', secondName='DILLER', pseudonim='TWOBOYS'}, " +
                "Author{id=9, firstName='MALOY1', secondName='DILLER', pseudonim='TWOBOYS1'}, " +
                "Author{id=10, firstName='MALOY2', secondName='DILLER1', pseudonim='TWOBOYS2'}" +
                "]";
        String actual = authorList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void insert() throws DaoException {

        Author author = new Author(11,
                "1",
                "Fufa",
                "Orochimaru");

        authorDAO.insert(author);
        Optional<Author> authorOptional = authorDAO.getByPK(11);

        String actual = authorOptional.get().toString();
        String expected = author.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DaoException {

        authorDAO.delete(2);
        List<Author> authorList = authorDAO.getAll();

        String expected = "[" +
                "Author{id=1, firstName='UNKNOWN', secondName='UNKNOWN', pseudonim='UNKNOWN'}, " +
                "Author{id=3, firstName='IOSIPH', secondName='STALIN', pseudonim='SLAVA KPSS'}, " +
                "Author{id=4, firstName='LOSKAH', secondName='ANTON', pseudonim='MORGENSTERN'}, " +
                "Author{id=5, firstName='DVERI', secondName='ANON', pseudonim='STONE'}, " +
                "Author{id=6, firstName='SPICE', secondName='SNAF', pseudonim='LSP'}, " +
                "Author{id=7, firstName='ANDREY', secondName='KIRILL', pseudonim='FLOVERS'}, " +
                "Author{id=8, firstName='MALOY', secondName='DILLER', pseudonim='TWOBOYS'}, " +
                "Author{id=9, firstName='MALOY1', secondName='DILLER', pseudonim='TWOBOYS1'}, " +
                "Author{id=10, firstName='MALOY2', secondName='DILLER1', pseudonim='TWOBOYS2'}" +
                "]";
        String actual = authorList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void update() throws DaoException {

        Author author = new Author(2,
                "Fifa",
                "Fufa",
                "Orochimaru");

        authorDAO.update(author);
        Optional<Author> authorOptional = authorDAO.getByPK(2);

        String actual = authorOptional.get().toString();
        String expected = author.toString();

        assertEquals(expected, actual);
    }
}