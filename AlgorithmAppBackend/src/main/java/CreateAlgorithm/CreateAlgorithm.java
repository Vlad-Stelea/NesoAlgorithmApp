package CreateAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.AlgorithmDAO;

public class CreateAlgorithm implements RequestHandler<CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse>, CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse>> {

    public LambdaLogger logger = null;

    @Override
    public CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> handleRequest(CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Algorithm...");

        CreateAlgorithmResponse response;
        logger.log("Create classification: " + req.toString());

        CreateAlgorithmHandler handler = new CreateAlgorithmHandler(new AlgorithmDAO());

        CreateAlgorithmEvent<CreateAlgorithmRequest, CreateAlgorithmResponse> result = handler.handle(req);

        return new CreateAlgorithmEvent<>(req.getRequest(), result.getResponse());

    }


}
