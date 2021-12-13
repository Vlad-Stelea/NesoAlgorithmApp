package GetRegisteredUsers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.UsersDAO;

public class GetRegisteredUsers implements RequestHandler<GetRegisteredUsersRequest, GetRegisteredUsersResponse> {

    GetRegisteredUsersHandler handler;

    public GetRegisteredUsers() {
        handler = new GetRegisteredUsersHandler(new UsersDAO());
    }

    @Override
    public GetRegisteredUsersResponse handleRequest(GetRegisteredUsersRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Loading registered users");

        return handler.handle(request);
    }
}
