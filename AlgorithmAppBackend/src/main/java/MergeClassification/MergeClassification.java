package MergeClassification;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ClassificationDAO;

public class MergeClassification implements RequestHandler<MergeClassificationRequest, MergeClassificationResponse> {

    public LambdaLogger logger = null;
    MergeClassificationHandler handler;

    public MergeClassification() { handler = new MergeClassificationHandler(new ClassificationDAO()); }
    public MergeClassification(ClassificationDAO dao) {
        handler = new MergeClassificationHandler(dao);
    }

    @Override
    public MergeClassificationResponse handleRequest(MergeClassificationRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to merge an Classification...");
        logger.log("Merge Classification request: " + req.toString());

        return handler.handle(req);
    }
}
