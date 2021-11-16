package lambda;

import CreateProblemInstance.CreateProblemInstanceHandler;
import CreateProblemInstance.CreateProblemInstanceRequest;
import CreateProblemInstance.CreateProblemInstanceResponse;
import db.ProblemInstanceDAO;
import entities.ProblemInstance;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProblemInstanceTest extends LambdaTest {

    ProblemInstance probInstance;
    ProblemInstanceDAO dao;
    CreateProblemInstanceHandler cpiHandler;
    CreateProblemInstanceRequest req;

    @Before
    public void setup() {
        dao = mock(ProblemInstanceDAO.class);
        probInstance = new ProblemInstance("cpi_test", "cpiAlgo_test");
        cpiHandler = new CreateProblemInstanceHandler(dao);
        req = new CreateProblemInstanceRequest("cpi_uuid_test", "cpi_test", "cpi_url_test", "cpiAlgo_test");
    }

    @Test
    public void testCreateProblemInstance() throws SQLException {
        // add the problem instance and make sure we get the correct response
        when(dao.createProblemInstance("cpi_uuid_test", "cpi_test", "cpi_url_test", "cpiAlgo_test")).thenReturn(true);
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        assertEquals(handleResult.response, "cpi_uuid_test,cpi_test,cpi_url_test,cpiAlgo_test");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testFailCreateProblemInstance() throws SQLException {
        // add the problem instance, mock that the problem instance was added already, and ensure the handler responds appropriately
        when(dao.createProblemInstance("cpi_uuid_test", "cpi_test", "cpi_url_test", "cpiAlgo_test")).thenReturn(false);
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        assertEquals(handleResult.response, "cpi_uuid_test (cpi_test)");
        assertEquals(handleResult.httpCode, 409);
        assertEquals(handleResult.error, "Problem instance already exists.");
    }



}
