package CreateBenchmark;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.BenchmarkDAO;

public class CreateBenchmark implements RequestHandler<CreateBenchmarkRequest, CreateBenchmarkResponse> {


    public LambdaLogger logger = null;
    CreateBenchmarkHandler handler;

    public CreateBenchmark() {
        handler = new CreateBenchmarkHandler(new BenchmarkDAO());
    }

    @Override
    public CreateBenchmarkResponse handleRequest(CreateBenchmarkRequest req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Benchmark...");
        logger.log("Create Benchmark request: " + req.toString());

        return handler.handle(req);

    }
}
