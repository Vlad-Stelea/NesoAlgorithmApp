package RemoveAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;
import db.BenchmarkDAO;
import db.ImplementationDAO;
import db.ProblemInstanceDAO;

public class RemoveAlgorithm implements RequestHandler<RemoveAlgorithmRequest, RemoveAlgorithmResponse> {

    public LambdaLogger logger = null;
    RemoveAlgorithmHandler handler;

    public RemoveAlgorithm() {
        handler = new RemoveAlgorithmHandler(new AlgorithmDAO(), new ImplementationDAO(), new BenchmarkDAO(), new ProblemInstanceDAO());
    }

    @Override
    public RemoveAlgorithmResponse handleRequest(RemoveAlgorithmRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Algorithm...");
        logger.log("Remove Implementation request: " + req.toString());

        return handler.handle(req);
    }

}
