package GetAlgorithmPage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.*;

public class GetAlgorithmPage implements RequestHandler<GetAlgorithmPageRequest, GetAlgorithmPageResponse> {

    public LambdaLogger logger = null;
    GetAlgorithmPageHandler handler;

    public GetAlgorithmPage() {
        handler = new GetAlgorithmPageHandler(new AlgorithmDAO(), new ImplementationDAO(), new ProblemInstanceDAO(), new MachineConfigurationDAO(), new BenchmarkDAO());
    }

    @Override
    public GetAlgorithmPageResponse handleRequest(GetAlgorithmPageRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to get the algorithm page...");

        return handler.handle(req);
    }

}
