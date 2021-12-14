package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ProblemInstanceTest {

    private ProblemInstance problemInstance;

    @Before
    public void setup() {
        problemInstance = new ProblemInstance("probInstanceEntityTest", "probInstanceEntityTest_algo");
    }

    @Test
    public void testEquals() {
        assertNotEquals(200, problemInstance);
        assertEquals(problemInstance, problemInstance);
        assertEquals(problemInstance, new ProblemInstance("probInstanceEntityTest", "probInstanceEntityTest_algo"));
        assertNotEquals(problemInstance, new ProblemInstance("NOT_probInstanceEntityTest", "probInstanceEntityTest_algo"));
    }

    @Test
    public void testGetsAndSets() {
        String uuid = "uuid";
        String algoName = "algoName";
        String datasetUrl = "datasetUrl";
        String probInstancename = "probInstanceName";

        ProblemInstance pInstance = new ProblemInstance(uuid, algoName);

        pInstance.setDatasetURL(datasetUrl);
        pInstance.setAlgoName(algoName);
        pInstance.setProbInstanceUUID(uuid);
        pInstance.setProbInstanceName(probInstancename);

        assertEquals(uuid, pInstance.getProbInstanceUUID());
        assertEquals(algoName, pInstance.getAlgoName());
        assertEquals(datasetUrl, pInstance.getDatasetURL());
        assertEquals(probInstancename, pInstance.getProbInstanceName());

    }

}
