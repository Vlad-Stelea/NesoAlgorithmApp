package LogUserAction;

import db.IActivityLogDAO;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLogUserActionHandler {
    @Test
    public void testHandleSuccess() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenReturn(true);
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Create request
        String username = "username";
        String action = "action";
        LogUserActionRequest request = new LogUserActionRequest(username, action);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);

        // Test that all the important vars are handled correctly
        assertEquals(200, response.statusCode);
        assertEquals(username, response.getUsername());
        assertEquals(action, response.getAction());
    }

    @Test
    public void testHandleException() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenThrow(new SQLException());
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Create request
        String username = "username";
        String action = "action";
        LogUserActionRequest request = new LogUserActionRequest(username, action);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);
        assertEquals(400, response.statusCode);
    }

    @Test
    public void testError() throws SQLException {
        // Initialize the DAO and handler
        IActivityLogDAO mockActivityLogDAO = mock(IActivityLogDAO.class);
        when(mockActivityLogDAO.createUserAction(any(), any(), any(), any())).thenReturn(false);
        LogUserActionHandler handler = new LogUserActionHandler(mockActivityLogDAO);

        // Create request
        String username = "username";
        String action = "action";
        LogUserActionRequest request = new LogUserActionRequest(username, action);

        // Handle the request
        LogUserActionResponse response = handler.handle(request);
        assertEquals(400, response.statusCode);
    }
}
