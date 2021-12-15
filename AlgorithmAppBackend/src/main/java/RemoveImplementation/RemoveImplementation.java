package RemoveImplementation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.BenchmarkDAO;
import db.ImplementationDAO;

public class RemoveImplementation implements RequestHandler<RemoveImplementationRequest, RemoveImplementationResponse> {

    public LambdaLogger logger = null;
    RemoveImplementationHandler handler;

    public RemoveImplementation() {
        handler = new RemoveImplementationHandler(new ImplementationDAO(), new BenchmarkDAO());
    }

    public RemoveImplementation(RemoveImplementationHandler handler) {
        this.handler = handler;
    }

    @Override
    public RemoveImplementationResponse handleRequest(RemoveImplementationRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Implementation...");
        logger.log("Remove Implementation request: " + req.toString());

        return handler.handle(req);
    }

}
