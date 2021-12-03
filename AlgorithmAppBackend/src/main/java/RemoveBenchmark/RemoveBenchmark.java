package RemoveBenchmark;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.BenchmarkDAO;

public class RemoveBenchmark implements RequestHandler<RemoveBenchmarkRequest, RemoveBenchmarkResponse> {

    public LambdaLogger logger = null;
    RemoveBenchmarkHandler handler;

    public RemoveBenchmark() {
        handler = new RemoveBenchmarkHandler(new BenchmarkDAO());
    }

    @Override
    public RemoveBenchmarkResponse handleRequest(RemoveBenchmarkRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Benchmark...");
        logger.log("Remove Benchmark request: " + req.toString());

        return handler.handle(req);
    }

}
