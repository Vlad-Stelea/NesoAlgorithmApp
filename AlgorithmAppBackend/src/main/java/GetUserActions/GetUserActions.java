package GetUserActions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ActivityLogDAO;

public class GetUserActions implements RequestHandler<GetUserActionsRequest, GetUserActionsResponse> {

    public LambdaLogger logger = null;
    GetUserActionsHandler handler;

    public GetUserActions() {
        handler = new GetUserActionsHandler(new ActivityLogDAO());
    }

    @Override
    public GetUserActionsResponse handleRequest(GetUserActionsRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to get the user actions ...");

        return handler.handle(req);
    }

}
