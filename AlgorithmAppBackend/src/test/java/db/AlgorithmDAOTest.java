package db;


import entities.*;
import db.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AlgorithmDAOTest {


    static AlgorithmDAO dao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new AlgorithmDAO();
    }

    @Test
    public void testCreateAlgorithm() throws SQLException {
        // create an Algorithm then check that we can find it in the database
        dao.removeAlgorithm("efsDaoTest");
        assertTrue(dao.createAlgorithm("efsDaoTest", null));
        Algorithm returned = dao.getAlgorithm("efsDaoTest");
        // clean up
        assertTrue(dao.removeAlgorithm("efsDaoTest"));
        Algorithm expectedAlgorithm = new Algorithm("efsDaoTest");
        assertEquals(expectedAlgorithm, returned);

    }

    @Test
    public void testRemoveAlgorithm() throws SQLException {
        dao.removeAlgorithm("efsdaoTest2");
        // create an Algorithm then check that we can find it in the database
        assertTrue(dao.createAlgorithm("efsdaoTest2", null));
        // check that we can remove the Algorithm, then check it doesn't exist
        assertTrue(dao.removeAlgorithm("efsdaoTest2"));
        assertNull(dao.getAlgorithm("efsdaoTest2"));
    }

    @Test
    public void testGetAlgorithm() throws SQLException {
        dao.removeAlgorithm("efsdaoTest3");
        // shouldn't be able to get a non-existent Algorithm
        assertNull(dao.getAlgorithm("efsdaoTest3"));

        // create an Algorithm then get it from the database
        assertTrue(dao.createAlgorithm("efsdaoTest3", null));
        Algorithm actualAlgorithm = dao.getAlgorithm("efsdaoTest3");
        assertTrue(dao.removeAlgorithm("efsdaoTest3"));
        Algorithm expectedAlgorithm = new Algorithm("efsdaoTest3");
        assertEquals(expectedAlgorithm, actualAlgorithm);


    }

    @Test
    public void testGetAllAlgorithms() throws SQLException {
        // create an Algorithm then get it from the database
        dao.createAlgorithm("efsdaoTest4", null);
        dao.createAlgorithm("efsdaoTest5", null);
        dao.createAlgorithm("efsdaoTest6", null);
        dao.createAlgorithm("efsdaoTest7", null);
        ArrayList<Algorithm> rets = dao.getAllAlgorithms();
        dao.removeAlgorithm("efsdaoTest4");
        dao.removeAlgorithm("efsdaoTest5");
        dao.removeAlgorithm("efsdaoTest6");
        dao.removeAlgorithm("efsdaoTest7");
        ArrayList<Algorithm> retEmpty = dao.getAllAlgorithms();

        assertTrue(rets.contains(new Algorithm("efsdaoTest4")));
        assertTrue(rets.contains(new Algorithm("efsdaoTest5")));
        assertTrue(rets.contains(new Algorithm("efsdaoTest6")));
        assertTrue(rets.contains(new Algorithm("efsdaoTest7")));

        assertFalse(retEmpty.contains(new Algorithm("efsdaoTest4")));
        assertFalse(retEmpty.contains(new Algorithm("efsdaoTest5")));
        assertFalse(retEmpty.contains(new Algorithm("efsdaoTest6")));
        assertFalse(retEmpty.contains(new Algorithm("efsdaoTest7")));

   }

    @Test
    public void testReclassifyAlgorithm() throws SQLException {
        ClassificationDAO classDao = new ClassificationDAO();
        classDao.createClassification("algoTestClass1", null);
        classDao.createClassification("algoTestClass2", null);
        dao.createAlgorithm("algoToReclassifyTest", "algoTestClass1");

        // check that we can reclassify
        assertTrue(dao.reclassifyAlgorithm("algoToReclassifyTest", "algoTestClass2"));
        Algorithm expectedAlgo = new Algorithm("algoToReclassifyTest", "algoTestClass2");
        assertEquals(dao.getAlgorithm("algoToReclassifyTest"), expectedAlgo);

        // clean up
        dao.removeAlgorithm("algoToReclassifyTest");
        classDao.removeClassification("algoTestClass1");
        classDao.removeClassification("algoTestClass2");
    }

}
