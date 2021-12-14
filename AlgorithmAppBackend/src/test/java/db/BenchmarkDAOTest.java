package db;

import entities.Benchmark;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class BenchmarkDAOTest {

    static BenchmarkDAO dao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new BenchmarkDAO();
    }

    @Test
    public void testCreateBenchmark() throws SQLException {
        ClassificationDAO classDao = new ClassificationDAO();
        classDao.createClassification("bmClassTestName", null);
        AlgorithmDAO algoDao = new AlgorithmDAO();
        algoDao.createAlgorithm("bmAlgoTestName", "bmClassTestName");
        ProblemInstanceDAO piDao = new ProblemInstanceDAO();
        piDao.createProblemInstance("bmProbInstanceUUID", "bmPIName", "whocares.com", "bmAlgoTestName");
        ImplementationDAO implDao = new ImplementationDAO();
        implDao.createImplementation("bmImplTestName", "anotherUrl.com", "english", "bmAlgoTestName");
        MachineConfigurationDAO mcDao = new MachineConfigurationDAO();
        mcDao.createMachineConfiguration("bmMachineConfigTestName", 123, 456, "tortilla", 10);
        assertTrue(dao.createBenchmark("bmUUIDTest", "bmNameTest", 15, null, "bmAlgoTestName", "bmImplTestName", "bmMachineConfigTestName", "bmProbInstanceUUID"));
        ArrayList<Benchmark> actualBenchmarks = dao.getBenchmarkForAlgo("bmAlgoTestName");
        Benchmark expectedBenchmark = new Benchmark("bmUUIDTest", "bmNameTest", 15, null, "bmAlgoTestName", "bmImplTestName", "bmMachineConfigTestName", "bmProbInstanceUUID");

        // check that we were able to get the benchmark we just created
        assertTrue(actualBenchmarks.contains(expectedBenchmark));

        // clean up
        dao.removeBenchmark("bmUUIDTest");
        mcDao.removeMachineConfiguration("bmMachineConfigTestName");
        piDao.removeProblemInstance("bmProbInstanceUUID");
        implDao.removeImplementation("bmImplTestName", "bmAlgoTestName");
        algoDao.removeAlgorithm("bmAlgoTestName");
        classDao.removeClassification("bmClassTestName");
    }

    @Test
    public void testRemoveBenchmarks() throws SQLException {
        ClassificationDAO classDao = new ClassificationDAO();
        classDao.createClassification("bmClassTestName", null);
        AlgorithmDAO algoDao = new AlgorithmDAO();
        algoDao.createAlgorithm("bmAlgoTestName", "bmClassTestName");
        ProblemInstanceDAO piDao = new ProblemInstanceDAO();
        piDao.createProblemInstance("bmProbInstanceUUID", "bmPIName", "whocares.com", "bmAlgoTestName");
        ImplementationDAO implDao = new ImplementationDAO();
        implDao.createImplementation("bmImplTestName", "anotherUrl.com", "english", "bmAlgoTestName");
        MachineConfigurationDAO mcDao = new MachineConfigurationDAO();
        mcDao.createMachineConfiguration("bmMachineConfigTestName", 123, 456, "tortilla", 10);
        assertTrue(dao.createBenchmark("bmUUIDTest", "bmNameTest", 15, null, "bmAlgoTestName", "bmImplTestName", "bmMachineConfigTestName", "bmProbInstanceUUID"));

        // try to remove the benchmarks based on the algorithm name
        assertTrue(dao.removeBenchmarksByAlgorithm("bmAlgoTestName"));
        assertTrue(dao.createBenchmark("bmUUIDTest", "bmNameTest", 15, null, "bmAlgoTestName", "bmImplTestName", "bmMachineConfigTestName", "bmProbInstanceUUID"));

        // try to remove the benchmarks based on the problem instance UUID
        assertTrue(dao.removeBenchmarksByProbInstanceUUID("bmProbInstanceUUID"));
        assertTrue(dao.createBenchmark("bmUUIDTest", "bmNameTest", 15, null, "bmAlgoTestName", "bmImplTestName", "bmMachineConfigTestName", "bmProbInstanceUUID"));

        // try to remove the benchmarks based on the implementation name
        assertTrue(dao.removeBenchmarksByImplName("bmImplTestName", "bmAlgoTestName"));

        assertFalse(dao.removeBenchmark("bmUUIDTest"));
        mcDao.removeMachineConfiguration("bmMachineConfigTestName");
        piDao.removeProblemInstance("bmProbInstanceUUID");
        implDao.removeImplementation("bmImplTestName", "bmAlgoTestName");
        algoDao.removeAlgorithm("bmAlgoTestName");
        classDao.removeClassification("bmClassTestName");
    }

}
