package DeleteUser;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.DeleteUserResult;
import com.amazonaws.services.fis.model.ResourceNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDeleteUserHandler {
    final String username = "username";
    final DeleteUserRequest request = new DeleteUserRequest(username);

    @Test
    public void testSuccessfulDelete() {
        AWSCognitoIdentityProvider mockProvider = mock(AWSCognitoIdentityProvider.class);
        when(mockProvider.adminDeleteUser(any())).thenReturn(new AdminDeleteUserResult());

        DeleteUserHandler handler = new DeleteUserHandler(mockProvider);

        DeleteUserResponse expectedResult = new DeleteUserResponse(200, username);

        assertEquals(expectedResult, handler.handle(request));
    }

    @Test
    public void testErrorDelete() {
        AWSCognitoIdentityProvider mockProvider = mock(AWSCognitoIdentityProvider.class);
        when(mockProvider.adminDeleteUser(any())).thenThrow(new ResourceNotFoundException("Error resource not found :("));

        DeleteUserHandler handler = new DeleteUserHandler(mockProvider);

        DeleteUserResponse expectedResult = new DeleteUserResponse(400);
        expectedResult.setError("Error deleting the user");

        assertEquals(expectedResult, handler.handle(request));
    }
}
