package CreateClassification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.ClassificationDAO;

public class CreateClassification implements RequestHandler<CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse>, CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse>> {

    public LambdaLogger logger = null;

    @Override
    public CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> handleRequest(CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create classification...");

        CreateClassificationResponse response;
        logger.log("Create classification: " + req.toString());

        CreateClassificationHandler handler = new CreateClassificationHandler(new ClassificationDAO());

        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> result = handler.handle(req);

        return new CreateClassificationEvent<>(req.getRequest(), result.getResponse());

    }


}
