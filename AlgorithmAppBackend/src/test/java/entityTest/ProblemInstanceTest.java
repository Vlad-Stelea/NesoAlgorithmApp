package entityTest;

import static org.junit.Assert.*;

import entities.Classification;
import entities.ProblemInstance;
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

}
