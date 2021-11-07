package CreateAlgorithm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;
import entities.Algorithm;

public class CreateAlgorithmHandler implements RequestHandler<CreateAlgorithmRequest,CreateAlgorithmResponse> {

    LambdaLogger logger;

    @Override
    public CreateAlgorithmResponse handleRequest(CreateAlgorithmRequest req, Context context)  {
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
    }

}