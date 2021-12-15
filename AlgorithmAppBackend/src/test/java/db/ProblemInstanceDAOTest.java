package db;

import entities.ProblemInstance;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProblemInstanceDAOTest {

    static ProblemInstanceDAO dao;

    @BeforeClass
    public static void setup() {
        dao = new ProblemInstanceDAO();
    }

    @Test
    public void testCreateProblemInstances() throws SQLException {
        ClassificationDAO classDao = new ClassificationDAO();
        classDao.createClassification("piClassTestName", null);
        AlgorithmDAO algoDao = new AlgorithmDAO();
        algoDao.createAlgorithm("piAlgoTestName", "piClassTestName");
        dao.createProblemInstance("piProbInstanceUUID1", "testPIName", "wpi.edu", "piAlgoTestName");
        dao.createProblemInstance("piProbInstanceUUID2", "testPIName2", "wpi.edu.com", "piAlgoTestName");

        ProblemInstance expectedPI1 = new ProblemInstance("piProbInstanceUUID1", "testPIName", "wpi.edu", "piAlgoTestName");
        ProblemInstance expectedPI2 = new ProblemInstance("piProbInstanceUUID2", "testPIName2", "wpi.edu.com", "piAlgoTestName");

        Optional<ProblemInstance> actualPI1 = dao.getProblemInstance("piProbInstanceUUID1");
        assertEquals(actualPI1.get(), expectedPI1);

        ArrayList<ProblemInstance> allPIs = dao.getAllAlgosProblemInstances("piAlgoTestName");
        assertTrue(allPIs.contains(expectedPI1));
        assertTrue(allPIs.contains(expectedPI2));

        assertTrue(dao.removeProblemInstancesByAlgorithm("piAlgoTestName"));
        assertFalse(dao.removeProblemInstance("piProbInstanceUUID1"));
        assertFalse(dao.removeProblemInstance("piProbInstanceUUID2"));
        algoDao.removeAlgorithm("piAlgoTestName");
        classDao.removeClassification("piClassTestName");
    }

}
