package LogUserAction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ActivityLogDAO;

public class LogUserAction implements RequestHandler<LogUserActionRequest, LogUserActionResponse> {
    LogUserActionHandler handler;

    public LogUserAction() {
        handler = new LogUserActionHandler(new ActivityLogDAO());
    }

    public LogUserAction(LogUserActionHandler handler) {
        this.handler = handler;
    }


    @Override
    public LogUserActionResponse handleRequest(LogUserActionRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("LogUserAction Request: " + request);
        return handler.handle(request);
    }
}
