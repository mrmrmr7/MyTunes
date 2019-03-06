package com.mrmrmr7.mytunes.dao.impl;

import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.entity.MusicSelection;
import com.mrmrmr7.mytunes.util.DBFill;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MusicSelectionDAOTest {
    public static MusicSelectionDAO musicSelectionDAO;

    @BeforeAll
    public static void daoInit() {
        musicSelectionDAO = new MusicSelectionDAO();
    }

    @AfterAll
    public static void daoDestroy() {
        musicSelectionDAO.closeConnection();
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
    void getByPK() throws DAOException {

        Optional<MusicSelection> musicSelection = musicSelectionDAO.getByPK(6);

        String actual = musicSelection.get().toString();
        String expected = "MusicSelection{" +
                "selection_id=6, " +
                "id=[51, 52, 53, 54, 55, 56, 57, 58, 59, 60], " +
                "compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]" +
                "}";

        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {

        List<MusicSelection> musicSelectionList = musicSelectionDAO.getAll();

        String actual = musicSelectionList.toString();
        String expected = "[" +
                "MusicSelection{selection_id=1, id=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=2, id=[11, 12, 13, 14, 15, 16, 17, 18, 19, 20], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=3, id=[21, 22, 23, 24, 25, 26, 27, 28, 29, 30], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=4, id=[31, 32, 33, 34, 35, 36, 37, 38, 39, 40], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=5, id=[41, 42, 43, 44, 45, 46, 47, 48, 49, 50], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=6, id=[51, 52, 53, 54, 55, 56, 57, 58, 59, 60], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=7, id=[61, 62, 63, 64, 65, 66, 67, 68, 69, 70], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=8, id=[71, 72, 73, 74, 75, 76, 77, 78, 79, 80], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=9, id=[81, 82, 83, 84, 85, 86, 87, 88, 89, 90], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=10, id=[91, 92, 93, 94, 95, 96, 97, 98, 99, 100], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void insert() throws DAOException {

        MusicSelection expected = new MusicSelection(
                5,
                11,
                4
        );

        musicSelectionDAO.insert(expected);

        Optional<MusicSelection> actual = musicSelectionDAO.getByPK(11);

        assertEquals(expected, actual.get());
    }

    @Test
    void delete() throws DAOException {

        musicSelectionDAO.delete(1);

        List<MusicSelection> musicSelectionList = musicSelectionDAO.getAll();

        String actual = musicSelectionList.toString();
        String expected = "[" +
                "MusicSelection{selection_id=2, id=[11, 12, 13, 14, 15, 16, 17, 18, 19, 20], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=3, id=[21, 22, 23, 24, 25, 26, 27, 28, 29, 30], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=4, id=[31, 32, 33, 34, 35, 36, 37, 38, 39, 40], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=5, id=[41, 42, 43, 44, 45, 46, 47, 48, 49, 50], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=6, id=[51, 52, 53, 54, 55, 56, 57, 58, 59, 60], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=7, id=[61, 62, 63, 64, 65, 66, 67, 68, 69, 70], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=8, id=[71, 72, 73, 74, 75, 76, 77, 78, 79, 80], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=9, id=[81, 82, 83, 84, 85, 86, 87, 88, 89, 90], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}, " +
                "MusicSelection{selection_id=10, id=[91, 92, 93, 94, 95, 96, 97, 98, 99, 100], compositionIdList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    void update() throws SQLException, DAOException {

        MusicSelection musicSelection = new MusicSelection(
                31,
                4,
                3
        );

        musicSelectionDAO.update(musicSelection);

        String expected = "MusicSelection{selection_id=4, id=[31, 32, 33, 34, 35, 36, 37, 38, 39, 40], compositionIdList=[3, 2, 3, 4, 5, 6, 7, 8, 9, 10]}";
        String actual = musicSelectionDAO.getByPK(4).get().toString();

        assertEquals(expected, actual);
    }
}