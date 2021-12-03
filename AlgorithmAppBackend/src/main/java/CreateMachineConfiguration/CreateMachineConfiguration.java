package CreateMachineConfiguration;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.MachineConfigurationDAO;

public class CreateMachineConfiguration implements RequestHandler<CreateMachineConfigurationRequest, CreateMachineConfigurationResponse> {

    public LambdaLogger logger = null;
    CreateMachineConfigurationHandler handler;

    public CreateMachineConfiguration() {
        handler = new CreateMachineConfigurationHandler(new MachineConfigurationDAO());
    }

    @Override
    public CreateMachineConfigurationResponse handleRequest(CreateMachineConfigurationRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Machine Configuration...");
        logger.log("Create Machine Configuration request: " + req.toString());

        return handler.handle(req);
    }

}
