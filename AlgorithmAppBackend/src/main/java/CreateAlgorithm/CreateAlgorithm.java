package CreateAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.AlgorithmDAO;

public class CreateAlgorithm implements RequestHandler<CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse>, CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse>> {

    public LambdaLogger logger = null;

    @Override
    public CreateClassificationEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> handleRequest(CreateClassificationEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Algorithm...");

        CreateAlgorithmResponse response;
        logger.log("Create classification: " + req.toString());

        CreateALgorithmHandler handler = new CreateAlgorithmHandler(new AlgorithmDAO());

        CreateClassificationEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> result = handler.handle(req);

        return new CreateAlgorithmEvent<>(req.getRequest(), result.getResponse());

        /*
        logger = context.getLogger();
        logger.log(req.toString());

        CreateAlgorithmResponse response;
        try {
            AlgorithmDAO db = new AlgorithmDAO();
            db.createAlgorithm(req.algoName, req.className);
            response = new CreateAlgorithmResponse(req.algoName);

        } catch (Exception e) {
            response = new CreateAlgorithmResponse("Unable to create Algorithm: " + req.algoName + "(" + e.getMessage() + ")", 400);
        }

        return response;
         */

    }


}
