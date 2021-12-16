package DeleteUser;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import db.IUsersDAO;

public class DeleteUserHandler {
    IUsersDAO userDAO;

    public DeleteUserHandler(IUsersDAO userDAO) {
        this.userDAO = userDAO;
    }

    public DeleteUserResponse handle(DeleteUserRequest request) {

        String username = request.username;
        AdminDeleteUserRequest deleteUserRequest = new AdminDeleteUserRequest();
        deleteUserRequest.setUsername(username);
        try {
            userDAO.deleteUser(deleteUserRequest.getUsername());
            return new DeleteUserResponse(200, username);
        } catch (Exception e) {
            DeleteUserResponse response = new DeleteUserResponse(400);
            response.setError("Error deleting the user");
            return response;
        }
    }
}
