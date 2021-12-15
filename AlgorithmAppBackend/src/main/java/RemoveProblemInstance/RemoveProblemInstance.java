package RemoveProblemInstance;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.BenchmarkDAO;
import db.ProblemInstanceDAO;

public class RemoveProblemInstance implements RequestHandler<RemoveProblemInstanceRequest, RemoveProblemInstanceResponse> {

    public LambdaLogger logger = null;
    RemoveProblemInstanceHandler handler;

    public RemoveProblemInstance() {
        handler = new RemoveProblemInstanceHandler(new ProblemInstanceDAO(), new BenchmarkDAO());
    }

    public RemoveProblemInstance(RemoveProblemInstanceHandler handler) {
        this.handler = handler;
    }

    @Override
    public RemoveProblemInstanceResponse handleRequest(RemoveProblemInstanceRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Problem Instance...");
        logger.log("Remove Problem Instance request: " + req.toString());

        return handler.handle(req);
    }

}
