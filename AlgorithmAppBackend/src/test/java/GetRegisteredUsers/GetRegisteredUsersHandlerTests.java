package GetRegisteredUsers;

import db.IUsersDAO;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetRegisteredUsersHandlerTests {

    @Test
    public void testSuccessHandle() throws SQLException {
        List<String> expectedUsers = Arrays.asList("test", "otherUser", "last usr");
        IUsersDAO mockUsersDao = mock(IUsersDAO.class);
        when(mockUsersDao.getRegisteredUsers()).thenReturn(expectedUsers);

        GetRegisteredUsersHandler handler = new GetRegisteredUsersHandler(mockUsersDao);
        assertEquals(expectedUsers, handler.handle(new GetRegisteredUsersRequest()).getUsers());
    }

    @Test
    public void testErrorHandle() throws SQLException {
        IUsersDAO mockUsersDao = mock(IUsersDAO.class);
        when(mockUsersDao.getRegisteredUsers()).thenThrow(new SQLException("Error"));

        GetRegisteredUsersHandler handler = new GetRegisteredUsersHandler(mockUsersDao);
        GetRegisteredUsersResponse response = handler.handle(new GetRegisteredUsersRequest());

        assertEquals(400, response.statusCode);
        assertEquals("Error" ,response.error);
    }
}
