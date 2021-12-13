package lambda;

import RemoveImplementation.RemoveImplementationHandler;
import RemoveImplementation.RemoveImplementationRequest;
import RemoveImplementation.RemoveImplementationResponse;
import db.BenchmarkDAO;
import db.ImplementationDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveImplementationTest {

    ImplementationDAO implDAO;
    BenchmarkDAO benchmarkDAO;

    RemoveImplementationHandler riHandler;
    RemoveImplementationRequest req;

    @Before
    public void setup() {
        implDAO = mock(ImplementationDAO.class);
        benchmarkDAO = mock(BenchmarkDAO.class);
        riHandler = new RemoveImplementationHandler(implDAO, benchmarkDAO);
        req = new RemoveImplementationRequest("ri_uuid_test","ri_uuid_test_algo");
    }

    @Test
    public void testRemoveProblemInstance() throws SQLException {
        // remove the problem instance and mock a true response
        when(implDAO.removeImplementation("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(true);
        when(benchmarkDAO.removeBenchmarksByImplName("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(true);
        RemoveImplementationResponse handleResult = riHandler.handle(req);
        assertEquals(handleResult.getImplName(), "ri_uuid_test");
        assertEquals(handleResult.getAlgoName(), "ri_uuid_test_algo");
        assertEquals(handleResult.getHttpCode(), 200);
    }

    @Test
    public void testFailRemoveProblemInstance() throws SQLException {
        // mock that the problem instance couldn't be found and check our error response is correct
        when(implDAO.removeImplementation("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(false);
        when(benchmarkDAO.removeBenchmarksByImplName("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(false);
        RemoveImplementationResponse handleResult = riHandler.handle(req);
        assertEquals(handleResult.getHttpCode(), 404);
        assertEquals(handleResult.getError(), "Implementation not found.");
    }

}
