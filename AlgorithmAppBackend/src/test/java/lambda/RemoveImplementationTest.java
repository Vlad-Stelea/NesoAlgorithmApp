package lambda;

import RemoveImplementation.RemoveImplementationHandler;
import RemoveImplementation.RemoveImplementationRequest;
import RemoveImplementation.RemoveImplementationResponse;
import db.ImplementationDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveImplementationTest {

    ImplementationDAO dao;
    RemoveImplementationHandler riHandler;
    RemoveImplementationRequest req;

    @Before
    public void setup() {
        dao = mock(ImplementationDAO.class);
        riHandler = new RemoveImplementationHandler(dao);
        req = new RemoveImplementationRequest("ri_uuid_test,ri_uuid_test_algo");
    }

    @Test
    public void testRemoveProblemInstance() throws SQLException {
        // remove the problem instance and mock a true response
        when(dao.removeImplementation("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(true);
        RemoveImplementationResponse handleResult = riHandler.handle(req);
        assertEquals(handleResult.getImplementationID(), "ri_uuid_test,ri_uuid_test_algo");
        assertEquals(handleResult.getHttpCode(), 200);
    }

    @Test
    public void testFailRemoveProblemInstance() throws SQLException {
        // mock that the problem instance couldn't be found and check our error response is correct
        when(dao.removeImplementation("ri_uuid_test", "ri_uuid_test_algo")).thenReturn(false);
        RemoveImplementationResponse handleResult = riHandler.handle(req);
        assertEquals(handleResult.getImplementationID(), "ri_uuid_test,ri_uuid_test_algo");
        assertEquals(handleResult.getHttpCode(), 404);
        assertEquals(handleResult.getError(), "Implementation not found.");
    }

}
