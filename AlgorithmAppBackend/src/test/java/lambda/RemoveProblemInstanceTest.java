package lambda;

import RemoveProblemInstance.RemoveProblemInstance;
import RemoveProblemInstance.RemoveProblemInstanceHandler;
import RemoveProblemInstance.RemoveProblemInstanceRequest;
import RemoveProblemInstance.RemoveProblemInstanceResponse;
import com.amazonaws.services.lambda.runtime.Context;
import db.BenchmarkDAO;
import db.ProblemInstanceDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveProblemInstanceTest {

    ProblemInstanceDAO problemInstanceDAO;
    BenchmarkDAO benchmarkDAO;
    RemoveProblemInstanceHandler rpiHandler;
    RemoveProblemInstanceRequest req;

    @Before
    public void setup() {
        problemInstanceDAO = mock(ProblemInstanceDAO.class);
        benchmarkDAO = mock(BenchmarkDAO.class);
        rpiHandler = new RemoveProblemInstanceHandler(problemInstanceDAO, benchmarkDAO);
        req = new RemoveProblemInstanceRequest("rpi_uuid_test");
    }

    @Test
    public void testRemoveProblemInstance() throws SQLException {
        // remove the problem instance and mock a true response
        when(problemInstanceDAO.removeProblemInstance("rpi_uuid_test")).thenReturn(true);
        when(benchmarkDAO.removeBenchmarksByProbInstanceUUID("rpi_uuid_test")).thenReturn(true);
        RemoveProblemInstanceResponse handleResult = rpiHandler.handle(req);
        assertEquals(handleResult.problemInstanceID, "rpi_uuid_test");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testFailRemoveProblemInstance() throws SQLException {
        // mock that the problem instance couldn't be found and check our error response is correct
        when(problemInstanceDAO.removeProblemInstance("rpi_uuid_test")).thenReturn(false);
        when(benchmarkDAO.removeBenchmarksByProbInstanceUUID("rpi_uuid_test")).thenReturn(false);
        RemoveProblemInstanceResponse handleResult = rpiHandler.handle(req);
        assertEquals(handleResult.problemInstanceID, "rpi_uuid_test");
        assertEquals(handleResult.httpCode, 404);
        assertEquals(handleResult.error, "Problem instance not found.");
    }

    @Test
    public void testMainRemoveProblemInstance() throws SQLException {
        // mock the main lambda function
        RemoveProblemInstanceHandler mockHandler = mock(RemoveProblemInstanceHandler.class);
        RemoveProblemInstance mainLambda = new RemoveProblemInstance(mockHandler);
        RemoveProblemInstanceRequest mockRequest = new RemoveProblemInstanceRequest("some_UUID");
        Context mockContext = new TestContext();

        RemoveProblemInstanceResponse mockResponse = new RemoveProblemInstanceResponse("some_UUID", 200);
        when(mainLambda.handleRequest(mockRequest, mockContext)).thenReturn(mockResponse);
        RemoveProblemInstanceResponse receivedResponse = mainLambda.handleRequest(mockRequest, mockContext);

        assertEquals(mockResponse, receivedResponse);
        assertEquals(mockResponse.toString(), receivedResponse.toString());
    }
    
}
