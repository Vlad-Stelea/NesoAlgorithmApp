package lambda;

import LogUserAction.LogUserAction;
import LogUserAction.LogUserActionHandler;
import LogUserAction.LogUserActionRequest;
import LogUserAction.LogUserActionResponse;
import com.amazonaws.services.lambda.runtime.Context;
import db.IActivityLogDAO;
import entities.PrettyDate;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogUserActionTest {

    private String username;
    private String action;
    private LogUserActionRequest request;

    @Before
    public void setup() {
        // Create request
        username = "username";
        action = "action";
        request = new LogUserActionRequest(username, action);
    }

    @Test
    public void testHandleSuccess() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenReturn(true);
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);

        // Test that all the important vars are handled correctly
        assertEquals(200, response.getStatusCode());
        assertEquals(username, response.getUsername());
        assertEquals(action, response.getAction());
    }

    @Test
    public void testHandleException() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenThrow(new SQLException());
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void testError() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenReturn(false);
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void testRequestAndResponseClasses(){
        LogUserActionRequest req = new LogUserActionRequest();
        req.setAction("123");
        req.setUsername("234");
        assertEquals(req.getAction(), "123");
        assertEquals(req.getUsername(), "234");
        assertEquals(req.toString(), "{\"username\":\"234\",\"action\":\"123\"}");

        LogUserActionResponse response = new LogUserActionResponse();
        response.setAction("123");
        response.setActivityLogUUID("234");
        response.setUsername("345");
        PrettyDate d = new PrettyDate(new Date(1,2,3));
        response.setDate(d);
        response.setError("567");
        response.setStatusCode(123);
        assertEquals(response.getAction(), "123");
        assertEquals(response.getActivityLogUUID(), "234");
        assertEquals(response.getUsername(), "345");
        assertEquals(response.getDate(), d);
        assertEquals(response.getError(), "567");
        assertEquals(response.getStatusCode(), 123);
        assertEquals(response.toString(), "{\"statusCode\":123,\"activityLogUUID\":\"234\",\"username\":\"345\",\"action\":\"123\",\"date\":{\"month\":2,\"day\":0,\"year\":-99,\"hours\":0,\"minutes\":0,\"seconds\":0},\"error\":\"567\"}");
    }

    @Test
    public void testMainRemoveBenchmark() throws SQLException {
        // mock the main lambda function
        LogUserActionHandler mockHandler = mock(LogUserActionHandler.class);
        LogUserAction mainLambda = new LogUserAction(mockHandler);
        LogUserActionRequest mockRequest = new LogUserActionRequest("username", "did a thing");
        Context mockContext = new TestContext();

        LogUserActionResponse mockResponse = new LogUserActionResponse(200, "nothing");
        when(mainLambda.handleRequest(mockRequest, mockContext)).thenReturn(mockResponse);
        LogUserActionResponse receivedResponse = mainLambda.handleRequest(mockRequest, mockContext);

        assertEquals(mockResponse, receivedResponse);
        assertEquals(mockResponse.toString(), receivedResponse.toString());
    }

}
