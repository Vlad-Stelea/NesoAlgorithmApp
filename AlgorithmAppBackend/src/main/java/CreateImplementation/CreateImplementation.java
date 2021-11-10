package CreateImplementation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ImplementationDAO;

public class CreateImplementation implements RequestHandler<CreateImplementationRequest, CreateImplementationResponse> {

    public LambdaLogger logger = null;
    CreateImplementationHandler handler;

    public CreateImplementation() {
        handler = new CreateImplementationHandler(new ImplementationDAO());
    }

    @Override
    public CreateImplementationResponse handleRequest(CreateImplementationRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Implementation...");
        logger.log("Create Implementation request: " + req.toString());

        return handler.handle(req);

    }


}