package lambda;

import RemoveAlgorithm.RemoveAlgorithmHandler;
import RemoveAlgorithm.RemoveAlgorithmRequest;
import RemoveAlgorithm.RemoveAlgorithmResponse;
import db.AlgorithmDAO;
import db.BenchmarkDAO;
import db.ImplementationDAO;
import db.ProblemInstanceDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveAlgorithmTest {

    AlgorithmDAO algoDAO;
    ImplementationDAO implDAO;
    BenchmarkDAO benchmarkDAO;
    ProblemInstanceDAO probDAO;

    RemoveAlgorithmHandler raHandler;
    RemoveAlgorithmRequest req;

    @Before
    public void setup() {
        implDAO = mock(ImplementationDAO.class);
        benchmarkDAO = mock(BenchmarkDAO.class);
        probDAO = mock(ProblemInstanceDAO.class);
        algoDAO = mock(AlgorithmDAO.class);
        raHandler = new RemoveAlgorithmHandler(algoDAO, implDAO, benchmarkDAO, probDAO);
        req = new RemoveAlgorithmRequest("testAlgoName");
    }

    @Test
    public void testRemoveProblemInstance() throws SQLException {
        // remove the problem instance and mock a true response
        when(implDAO.removeImplementationsByAlgorithm("testAlgoName")).thenReturn(true);
        when(benchmarkDAO.removeBenchmarksByAlgorithm("testAlgoName")).thenReturn(true);
        when(probDAO.removeProblemInstancesByAlgorithm("testAlgoName")).thenReturn(true);
        when(algoDAO.removeAlgorithm("testAlgoName")).thenReturn(true);
        RemoveAlgorithmResponse handleResult = raHandler.handle(req);
        assertEquals(handleResult.getAlgoName(), "testAlgoName");
        assertEquals(handleResult.getHttpCode(), 200);
    }

    @Test
    public void testFailRemoveProblemInstance() throws SQLException {
        // mock that the problem instance couldn't be found and check our error response is correct
        when(implDAO.removeImplementationsByAlgorithm("testAlgoName")).thenReturn(true);
        when(benchmarkDAO.removeBenchmarksByAlgorithm("testAlgoName")).thenReturn(true);
        when(probDAO.removeProblemInstancesByAlgorithm("testAlgoName")).thenReturn(true);
        when(algoDAO.removeAlgorithm("testAlgoName")).thenReturn(false);
        RemoveAlgorithmResponse handleResult = raHandler.handle(req);
        assertEquals(handleResult.getAlgoName(), "testAlgoName");
        assertEquals(handleResult.getHttpCode(), 404);
        assertEquals(handleResult.getError(), "Algorithm not found.");
    }

    @Test
    public void testRequestAndResponseClasses() throws SQLException {
        RemoveAlgorithmRequest req = new RemoveAlgorithmRequest();
        req.setAlgoName("test");
        assertEquals(req.toString(), "{\"algoName\":\"test\"}");

        RemoveAlgorithmResponse response = new RemoveAlgorithmResponse("123", 123);
        assertEquals(response.toString(), "{\"algoName\":\"123\",\"httpCode\":123,\"error\":\"\"}");
    }

}
