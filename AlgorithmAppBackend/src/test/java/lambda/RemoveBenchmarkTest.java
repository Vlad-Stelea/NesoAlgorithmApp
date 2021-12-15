package lambda;

import RemoveBenchmark.*;

import com.amazonaws.services.lambda.runtime.Context;
import db.BenchmarkDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveBenchmarkTest {

    BenchmarkDAO dao;
    RemoveBenchmarkHandler rbmHandler;
    RemoveBenchmarkRequest req;

    @Before
    public void setup() {
        dao = mock(BenchmarkDAO.class);
        rbmHandler = new RemoveBenchmarkHandler(dao);
        req = new RemoveBenchmarkRequest("rbm_uuid_test");
    }

    @Test
    public void testRemoveBenchmark() throws SQLException {
        // remove the benchmark and mock a true response
        when(dao.removeBenchmark("rbm_uuid_test")).thenReturn(true);
        RemoveBenchmarkResponse handleResult = rbmHandler.handle(req);
        assertEquals(handleResult.getBenchmarkID(), "rbm_uuid_test");
        assertEquals(handleResult.getHttpCode(), 200);
    }

    @Test
    public void testFailRemoveBenchmark() throws SQLException {
        // mock that the benchmark couldn't be found and check our error response is correct
        when(dao.removeBenchmark("rbm_uuid_test")).thenReturn(false);
        RemoveBenchmarkResponse handleResult = rbmHandler.handle(req);
        assertEquals(handleResult.getBenchmarkID(), "rbm_uuid_test");
        assertEquals(handleResult.getHttpCode(), 404);
        assertEquals(handleResult.getError(), "Error: Benchmark with ID rbm_uuid_test not found.");
    }

    @Test
    public void testMainRemoveBenchmark() throws SQLException {
        // mock the main lambda function
        RemoveBenchmarkHandler mockHandler = mock(RemoveBenchmarkHandler.class);
        RemoveBenchmark mainLambda = new RemoveBenchmark(mockHandler);
        RemoveBenchmarkRequest mockRequest = new RemoveBenchmarkRequest("some_UUID");
        Context mockContext = new TestContext();

        RemoveBenchmarkResponse mockResponse = new RemoveBenchmarkResponse("some_UUID", 200);
        when(mainLambda.handleRequest(mockRequest, mockContext)).thenReturn(mockResponse);
        RemoveBenchmarkResponse receivedResponse = mainLambda.handleRequest(mockRequest, mockContext);

        assertEquals(mockResponse, receivedResponse);
        assertEquals(mockResponse.toString(), receivedResponse.toString());
    }

}
