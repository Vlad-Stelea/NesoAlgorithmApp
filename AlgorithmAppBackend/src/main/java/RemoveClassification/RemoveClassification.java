package RemoveClassification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;
import db.ClassificationDAO;

public class RemoveClassification implements RequestHandler<RemoveClassificationRequest, RemoveClassificationResponse> {

    public RemoveClassificationHandler handler;
    public LambdaLogger logger = null;

    public RemoveClassification() {
        handler = new RemoveClassificationHandler(new AlgorithmDAO(), new ClassificationDAO());
    }

    public RemoveClassificationResponse handleRequest(RemoveClassificationRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Classification...");
        logger.log("Remove Classification request: " + req.toString());

        return handler.handle(req);
    }

}
