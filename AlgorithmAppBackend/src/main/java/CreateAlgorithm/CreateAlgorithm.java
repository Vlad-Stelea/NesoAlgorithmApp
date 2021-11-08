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
        System.out.println(req.toString());

        logger.log("Create classification: efs" + req.toString());
        AlgorithmDAO a = new AlgorithmDAO();
        logger.log("\nconnected to db\n");

        CreateAlgorithmHandler handler = new CreateAlgorithmHandler(a);
        logger.log("\nconnected to db\n");
        CreateAlgorithmResponse result = handler.handle(req);
        logger.log("return");
        return result;

    }


}
