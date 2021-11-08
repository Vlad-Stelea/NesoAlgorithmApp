package CreateAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import db.AlgorithmDAO;

public class CreateAlgorithm implements RequestHandler<CreateAlgorithmRequest, CreateAlgorithmResponse> {

    public LambdaLogger logger = null;

    @Override
    public CreateAlgorithmResponse handleRequest(CreateAlgorithmRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Algorithm...");


        logger.log("Create classification: efs" + req.toString());

        CreateAlgorithmHandler handler = new CreateAlgorithmHandler(new AlgorithmDAO());
        CreateAlgorithmResponse result = handler.handle(req);
        return result;

    }


}
