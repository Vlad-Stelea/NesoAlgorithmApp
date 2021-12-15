package RemoveMachineConfiguration;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.MachineConfigurationDAO;

public class RemoveMachineConfiguration implements RequestHandler<RemoveMachineConfigurationRequest, RemoveMachineConfigurationResponse> {

    public LambdaLogger logger = null;
    RemoveMachineConfigurationHandler handler;

    public RemoveMachineConfiguration() {
        this.handler = new RemoveMachineConfigurationHandler(new MachineConfigurationDAO());
    }

    public RemoveMachineConfiguration(RemoveMachineConfigurationHandler handler) {
        this.handler = handler;
    }

    @Override
    public RemoveMachineConfigurationResponse handleRequest(RemoveMachineConfigurationRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to remove Machine Configuration...");
        logger.log("Remove Machine Configuration request: " + req.toString());

        return handler.handle(req);
    }

}
