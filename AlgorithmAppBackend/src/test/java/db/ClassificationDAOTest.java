package db;


import entities.Classification;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

public class ClassificationDAOTest {

    static ClassificationDAO dao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new ClassificationDAO();
    }

    @Test
    public void testCreateClassification() throws SQLException {
        // create a classification then check that we can find it in the database
        assertTrue(dao.createClassification("daoTest", null));
        Classification expectedClassification = new Classification("daoTest");
        assertEquals(expectedClassification, dao.getClassification("daoTest"));

        // clean up
        assertTrue(dao.removeClassification("daoTest"));
    }

    @Test
    public void testRemoveClassification() throws SQLException {
        // create a classification then check that we can find it in the database
        assertTrue(dao.createClassification("daoTest", null));
        assertTrue(dao.checkClassificationExists("daoTest"));

        // check that we can remove the classification, then check it doesn't exist
        assertTrue(dao.removeClassification("daoTest"));
        assertFalse(dao.checkClassificationExists("daoTest"));
    }

    @Test
    public void testGetClassification() throws SQLException {
        // shouldn't be able to get a non-existent classification
        assertNull(dao.getClassification("daoTest"));

        // create a classification then get it from the database
        assertTrue(dao.createClassification("daoTest", null));
        Classification expectedClassification = new Classification("daoTest");
        Classification actualClassification = dao.getClassification("daoTest");
        assertEquals(expectedClassification, actualClassification);

        // clean up
        assertTrue(dao.removeClassification("daoTest"));
    }

}
