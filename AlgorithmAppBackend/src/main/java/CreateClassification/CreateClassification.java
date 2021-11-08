package CreateClassification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.ClassificationDAO;

public class CreateClassification implements RequestHandler<CreateClassificationRequest, CreateClassificationResponse> {

    public LambdaLogger logger = null;
    CreateClassificationHandler handler;

    public CreateClassification() {
        handler = new CreateClassificationHandler(new ClassificationDAO());
    }

    @Override
    public CreateClassificationResponse handleRequest(CreateClassificationRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create classification...");
        logger.log("Create classification request: " + req.toString());

        handler = new CreateClassificationHandler(new ClassificationDAO());

        return handler.handle(req);

    }


}
