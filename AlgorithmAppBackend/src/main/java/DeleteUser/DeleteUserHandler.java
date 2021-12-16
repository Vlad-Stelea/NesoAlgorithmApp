package DeleteUser;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import db.IUsersDAO;

public class DeleteUserHandler {
    AWSCognitoIdentityProvider identityProvider;
    private static String registeredUsersPoolId = "us-east-2_dCo4oi6Kv";
    IUsersDAO userDAO;


    public DeleteUserHandler(AWSCognitoIdentityProvider identityProvider, IUsersDAO userDAO) {
        this.identityProvider = identityProvider;
        this.userDAO = userDAO;
    }

    public DeleteUserResponse handle(DeleteUserRequest request) {

        String username = request.username;
        AdminDeleteUserRequest deleteUserRequest = new AdminDeleteUserRequest();
        deleteUserRequest.setUsername(username);
        deleteUserRequest.setUserPoolId(registeredUsersPoolId);
        try {
            this.identityProvider.adminDeleteUser(deleteUserRequest);
            userDAO.deleteUser(deleteUserRequest.getUsername());
            return new DeleteUserResponse(200, username);
        } catch (Exception e) {
            DeleteUserResponse response = new DeleteUserResponse(400);
            response.setError("Error deleting the user");
            return response;
        }
    }

    public DeleteUserResponse handle2(DeleteUserRequest request, LambdaLogger logger) {
        logger.log("Start");
        String username = request.username;
        AdminDeleteUserRequest deleteUserRequest = new AdminDeleteUserRequest();
        logger.log("mid");
        deleteUserRequest.setUsername(username);
        deleteUserRequest.setUserPoolId(registeredUsersPoolId);
        logger.log("befor try");
        try {
            this.identityProvider.adminDeleteUser(deleteUserRequest);
            logger.log("after delete cog");
            userDAO.deleteUser(deleteUserRequest.getUsername());
            logger.log("after db");
            return new DeleteUserResponse(200, username);
        } catch (Exception e) {
            DeleteUserResponse response = new DeleteUserResponse(400);
            response.setError("Error deleting the user");
            return response;
        }
    }
}
