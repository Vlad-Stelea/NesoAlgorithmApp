package LogUserAction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ActivityLogDAO;

public class LogUserAction implements RequestHandler<LogUserActionRequest, LogUserActionResponse> {
    LogUserActionHandler handler;

    public LogUserAction() {
        handler = new LogUserActionHandler(new ActivityLogDAO());
    }


    @Override
    public LogUserActionResponse handleRequest(LogUserActionRequest request, Context context) {
        return handler.handle(request);
    }
}
