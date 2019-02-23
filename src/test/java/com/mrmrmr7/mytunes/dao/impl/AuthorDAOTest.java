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

    @AfterEach
    void deinit() throws InterruptedException, SQLException, IOException {
        DBFill.drop();
    }

    @Test
    void getInstance() {
        assertNotNull(authorDAO);
    }

    @Test
    public void getByPK() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

        Optional<Author> author = authorDAO.getByPK(1);
        String expected = "Author{id=1, firstName='Unknown', secondName='Unknown', pseudonim='Unknown'}";
        String actual = author.get().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll() throws SQLException, IOException, InterruptedException {
        DBFill.fill();

        List<Author> authorList = authorDAO.getAll();
        String expected = "[" +
                "Author{id=1, firstName='Unknown', secondName='Unknown', pseudonim='Unknown'}, " +
                "Author{id=2, firstName='Alexandr', secondName='Zaporozhtsev', pseudonim='Grand-Master'}, " +
                "Author{id=3, firstName='Iosiph', secondName='Stalin', pseudonim='Slava KPSS'}, " +
                "Author{id=4, firstName='LoshkAh', secondName='Anton', pseudonim='MORGENSTERN'}, " +
                "Author{id=5, firstName='dVeri', secondName='Anon', pseudonim='Stone'}, " +
                "Author{id=6, firstName='Spice', secondName='Snaf', pseudonim='LSP'}, " +
                "Author{id=7, firstName='Andrey', secondName='Kirill', pseudonim='FLOVERS'}, " +
                "Author{id=8, firstName='Maloy', secondName='Diller', pseudonim='TwoBoys'}, " +
                "Author{id=9, firstName='Maloy1', secondName='Diller1', pseudonim='TwoBoys1'}, " +
                "Author{id=10, firstName='Maloy2', secondName='Diller2', pseudonim='TwoBoys2'}" +
                "]";
        String actual = authorList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void insert() throws InterruptedException, SQLException, IOException, DAOException {
        DBFill.fill();

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
    public void delete() throws InterruptedException, SQLException, IOException {
        DBFill.fill();

        authorDAO.delete(2);
        List<Author> authorList = authorDAO.getAll();

        String expected = "[Author{id=1, firstName='Unknown', secondName='Unknown', pseudonim='Unknown'}, " +
                "Author{id=3, firstName='Iosiph', secondName='Stalin', pseudonim='Slava KPSS'}, " +
                "Author{id=4, firstName='LoshkAh', secondName='Anton', pseudonim='MORGENSTERN'}, " +
                "Author{id=5, firstName='dVeri', secondName='Anon', pseudonim='Stone'}, " +
                "Author{id=6, firstName='Spice', secondName='Snaf', pseudonim='LSP'}, " +
                "Author{id=7, firstName='Andrey', secondName='Kirill', pseudonim='FLOVERS'}, " +
                "Author{id=8, firstName='Maloy', secondName='Diller', pseudonim='TwoBoys'}, " +
                "Author{id=9, firstName='Maloy1', secondName='Diller1', pseudonim='TwoBoys1'}, " +
                "Author{id=10, firstName='Maloy2', secondName='Diller2', pseudonim='TwoBoys2'}]";
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