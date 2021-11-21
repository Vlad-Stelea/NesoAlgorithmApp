package lambda;

import CreateProblemInstance.CreateProblemInstanceHandler;
import CreateProblemInstance.CreateProblemInstanceRequest;
import CreateProblemInstance.CreateProblemInstanceResponse;
import CreateProblemInstance.IProblemInstanceStorage;
import db.ProblemInstanceDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProblemInstanceTest extends LambdaTest {

    ProblemInstanceDAO dao;
    CreateProblemInstanceHandler cpiHandler;
    CreateProblemInstanceRequest req;
    IProblemInstanceStorage storage;

    @Before
    public void setup() {
        dao = mock(ProblemInstanceDAO.class);
        storage = mock(IProblemInstanceStorage.class);
        cpiHandler = new CreateProblemInstanceHandler(dao, storage);
        req = new CreateProblemInstanceRequest("cpi_uuid_test", "cpi_test", "cpi_url_test", "cpiAlgo_test");
    }

    @Test
    public void testCreateProblemInstance() throws SQLException {
        // add the problem instance and make sure we get the correct response
        when(dao.hasProblemInstance("cpi_uuid_test")).thenReturn(false);
        when(dao.createProblemInstance(any(), any(), any(), any())).thenReturn(true);
        when(storage.storeProblemInstance(any(), any())).thenReturn("www.whocares.org");

        CreateProblemInstanceResponse expected = new CreateProblemInstanceResponse(
                200,
                "cpi_uuid_test",
                "cpi_test",
                "www.whocares.org",
                "cpiAlgo_test"
        );

        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        assertEquals(expected, handleResult);
    }

    @Test
    public void testFailCreateProblemInstance() throws SQLException {
        // add the problem instance, mock that the problem instance was added already, and ensure the handler responds appropriately
        when(dao.createProblemInstance("cpi_uuid_test", "cpi_test", "cpi_url_test", "cpiAlgo_test")).thenReturn(false);
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        CreateProblemInstanceResponse expected = new CreateProblemInstanceResponse(
                409,
                "Failed to create Problem Instance: Problem Instance cpi_uuid_test already exists."
        );

        assertEquals(expected, handleResult);
    }



}
