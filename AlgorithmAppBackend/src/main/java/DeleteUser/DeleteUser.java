package DeleteUser;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteUser implements RequestHandler<DeleteUserRequest, DeleteUserResponse> {

    DeleteUserHandler handler;

    public DeleteUser() {
        this.handler = new DeleteUserHandler(AWSCognitoIdentityProviderClientBuilder.defaultClient());
    }

    @Override
    public DeleteUserResponse handleRequest(DeleteUserRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Deleting user: " + request.getUsername());
        return this.handler.handle(request);
    }
}
