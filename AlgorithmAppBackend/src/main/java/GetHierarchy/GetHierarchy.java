package GetHierarchy;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;
import db.ClassificationDAO;
import db.ImplementationDAO;

public class GetHierarchy implements RequestHandler<Object, GetHierarchyResponse> {

    public LambdaLogger logger = null;
    GetHierarchyHandler handler;

    public GetHierarchy() {
        handler = new GetHierarchyHandler(new ClassificationDAO(), new AlgorithmDAO(), new ImplementationDAO());
    }

    @Override
    public GetHierarchyResponse handleRequest(Object req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to get the hierarchy...");

        return handler.handle();
    }

}
