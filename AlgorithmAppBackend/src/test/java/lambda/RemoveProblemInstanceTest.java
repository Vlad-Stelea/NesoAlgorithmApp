package lambda;

import RemoveProblemInstance.RemoveProblemInstanceHandler;
import RemoveProblemInstance.RemoveProblemInstanceRequest;
import RemoveProblemInstance.RemoveProblemInstanceResponse;
import db.ProblemInstanceDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveProblemInstanceTest {

    ProblemInstanceDAO dao;
    RemoveProblemInstanceHandler rpiHandler;
    RemoveProblemInstanceRequest req;

    @Before
    public void setup() {
        dao = mock(ProblemInstanceDAO.class);
        rpiHandler = new RemoveProblemInstanceHandler(dao);
        req = new RemoveProblemInstanceRequest("rpi_uuid_test");
    }

    @Test
    public void testRemoveProblemInstance() throws SQLException {
        // remove the problem instance and mock a true response
        when(dao.removeProblemInstance("rpi_uuid_test")).thenReturn(true);
        RemoveProblemInstanceResponse handleResult = rpiHandler.handle(req);
        assertEquals(handleResult.response, "rpi_uuid_test");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testFailRemoveProblemInstance() throws SQLException {
        // mock that the problem instance couldn't be found and check our error response is correct
        when(dao.removeProblemInstance("rpi_uuid_test")).thenReturn(false);
        RemoveProblemInstanceResponse handleResult = rpiHandler.handle(req);
        assertEquals(handleResult.response, "rpi_uuid_test");
        assertEquals(handleResult.httpCode, 404);
        assertEquals(handleResult.error, "Problem instance not found.");
    }
    
}
