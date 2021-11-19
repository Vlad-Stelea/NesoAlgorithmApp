package CreateProblemInstance;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ProblemInstanceDAO;

public class CreateProblemInstance implements RequestHandler<CreateProblemInstanceRequest, CreateProblemInstanceResponse> {

    public LambdaLogger logger = null;
    CreateProblemInstanceHandler handler;

    public CreateProblemInstance() {
        handler = new CreateProblemInstanceHandler(new ProblemInstanceDAO());
    }

    @Override
    public CreateProblemInstanceResponse handleRequest(CreateProblemInstanceRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Problem Instance...");
        logger.log("Create Problem Instance request: " + req.toString());

        return handler.handle(req);
    }

}
