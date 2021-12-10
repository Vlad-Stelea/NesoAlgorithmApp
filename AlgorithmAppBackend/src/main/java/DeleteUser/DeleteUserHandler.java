package DeleteUser;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;

public class DeleteUserHandler {
    AWSCognitoIdentityProvider identityProvider;
    private static String registeredUsersPoolId = "us-east-2_dCo4oi6Kv";

    public DeleteUserHandler(AWSCognitoIdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
    }

    public DeleteUserResponse handle(DeleteUserRequest request) {
        String username = request.username;
        AdminDeleteUserRequest deleteUserRequest = new AdminDeleteUserRequest();
        deleteUserRequest.setUsername(username);
        deleteUserRequest.setUserPoolId(registeredUsersPoolId);
        try {
            this.identityProvider.adminDeleteUser(deleteUserRequest);
            return new DeleteUserResponse(200, username);
        } catch (Exception e) {
            return new DeleteUserResponse(400, "Error deleting the user");
        }
    }
}
