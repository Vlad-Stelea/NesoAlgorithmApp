package ReclassifyAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;

public class ReclassifyAlgorithm implements RequestHandler<ReclassifyAlgorithmRequest, ReclassifyAlgorithmResponse> {

    public LambdaLogger logger = null;
    ReclassifyAlgorithmHandler handler;

    public ReclassifyAlgorithm(AlgorithmDAO dao) {
        handler = new ReclassifyAlgorithmHandler(dao);
    }

    @Override
    public ReclassifyAlgorithmResponse handleRequest(ReclassifyAlgorithmRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to reclassify an Algorithm...");
        logger.log("Reclassify Algorithm request: " + req.toString());

        return handler.handle(req);
    }

}
