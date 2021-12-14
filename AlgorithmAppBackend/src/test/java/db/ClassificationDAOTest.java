package db;

import entities.Classification;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

public class ClassificationDAOTest {

    static ClassificationDAO dao;

    @BeforeClass
    public static void setup() {
        dao = new ClassificationDAO();
    }

    @Test
    public void testCreateClassification() throws SQLException {
        // create a classification then check that we can find it in the database
        assertTrue(dao.createClassification("daoTest", null));
        Classification actualClassification = dao.getClassification("daoTest");

        // clean up before asserting
        assertTrue(dao.removeClassification("daoTest"));

        Classification expectedClassification = new Classification("daoTest");
        assertEquals(expectedClassification, actualClassification);
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

        // clean up before asserting
        assertTrue(dao.removeClassification("daoTest"));

        assertEquals(expectedClassification, actualClassification);
    }

    @Test
    public void testGetAllClassifications() throws SQLException {
        Classification c1 = new Classification("unique_daoTest1");
        Classification c2 = new Classification("unique_daoTest2");
        Classification c3 = new Classification("unique_daoTest3");

        // create a classification then get it from the database
        assertTrue(dao.createClassification(c1.getClassName(), null));
        assertTrue(dao.createClassification(c2.getClassName(), null));
        assertTrue(dao.createClassification(c3.getClassName(), null));

        // get all classifications in the database so we can check if they're there and found by getAllClassifications
        List<Classification> result = dao.getAllClassifications();

        // clean up before asserting
        dao.removeClassification(c1.getClassName());
        dao.removeClassification(c2.getClassName());
        dao.removeClassification(c3.getClassName());

        // check the classifications were obtained
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
        assertTrue(result.contains(c3));
    }

}
