package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorDAOTest {
    private static AuthorDAO authorDAO;

    @BeforeEach
    void init() throws InterruptedException, SQLException, IOException {
        authorDAO = AuthorDAO.getInstance();
        DBFill.createDB();
    }

    @Test
    void getInstance() {
        assertNotNull(authorDAO);
    }

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }



    @Test
    public void getByPK() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

        Optional<Author> author = authorDAO.getByPK(1);
        String expected = "Author{id=1, firstName='Miron', secondName='Yanovich', pseudonim='Oxxxymiron'}";
        String actual = author.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll() throws SQLException, IOException, InterruptedException {
        DBFill.fill();

        List<Author> authorList = authorDAO.getAll();
        String expected = "[" +
                "Author{id=1, firstName='Miron', secondName='Yanovich', pseudonim='Oxxxymiron'}, " +
                "Author{id=2, firstName='Alexandr', secondName='Zaporozhtsev', pseudonim='Grand-Master'}, " +
                "Author{id=3, firstName='Iosiph', secondName='Stalin', pseudonim='Slava KPSS'}" +
                "]";
        String actual = authorList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void insert() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

        Author author = new Author(4,
                "1",
                "Fufa",
                "Orochimaru");

        authorDAO.insert(author);
        Optional<Author> authorOptional = authorDAO.getByPK(4);

        String actual = authorOptional.get().toString();
        String expected = author.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void delete() throws InterruptedException, SQLException, IOException {
        DBFill.fill();

        authorDAO.delete(2);
        List<Author> authorList = authorDAO.getAll();

        String expected = "[" +
                "Author{id=1, firstName='Miron', secondName='Yanovich', pseudonim='Oxxxymiron'}, " +
                "Author{id=3, firstName='Iosiph', secondName='Stalin', pseudonim='Slava KPSS'}" +
                "]";
        String actual = authorList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void update() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

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