package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.ClassificationDAO;
import http.CreateClassificationRequest;
import http.CreateClassificationResponse;

public class CreateClassification implements RequestHandler<CreateClassificationRequest, CreateClassificationResponse> {

    public LambdaLogger logger = null;

    @Override
    public CreateClassificationResponse handleRequest(CreateClassificationRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create classification...");

        CreateClassificationResponse response;
        logger.log("Create classification: " + req.toString());

        ClassificationDAO dao = new ClassificationDAO();

        try {
            if(dao.createClassification(req.getName(), req.getParentClassName())) {
                response = new CreateClassificationResponse(req.getName() + "," + req.getParentClassName(), 200);
            } else {
                response = new CreateClassificationResponse(req.getName(), 409, "Classification already exists.");
            }

        } catch (Exception e) {
            response = new CreateClassificationResponse("Unable to create classification: " + req.getName() + " with parent " + req.getParentClassName() + "\n(" + e.getMessage() + ")", 400);
        }

        return response;

    }


}
