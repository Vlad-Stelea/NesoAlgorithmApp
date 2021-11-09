package GetHierarchy;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.AlgorithmDAO;
import db.ClassificationDAO;

public class GetHierarchy implements RequestHandler<Object, GetHierarchyResponse> {

    public LambdaLogger logger = null;
    GetHierarchyHandler handler;

    public GetHierarchy() {
        // TODO add implementation DAO
        //handler = new GetHierarchyHandler(null, null);
        handler = new GetHierarchyHandler(new ClassificationDAO(), new AlgorithmDAO());
        //handler = new GetHierarchyHandler(new ClassificationDAO(), new AlgorithmDAO(), new ImplementationDAO());
    }

    @Override
    public GetHierarchyResponse handleRequest(Object req, Context context) {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to get the hierarchy...");

        return handler.handle();
    }

}
