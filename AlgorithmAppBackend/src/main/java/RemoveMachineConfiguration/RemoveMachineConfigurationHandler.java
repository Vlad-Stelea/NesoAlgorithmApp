package RemoveMachineConfiguration;

import db.MachineConfigurationDAO;

import java.util.UUID;

public class RemoveMachineConfigurationHandler {

    MachineConfigurationDAO dao;

    public RemoveMachineConfigurationHandler(MachineConfigurationDAO dao) {
        this.dao = dao;
    }

    public RemoveMachineConfigurationResponse handle(RemoveMachineConfigurationRequest request) {
        try {
            String machineConfigurationID = request.getMachineConfigurationID().replaceAll("%20", " ");

            if(dao.removeMachineConfiguration(machineConfigurationID)) {
                return new RemoveMachineConfigurationResponse(machineConfigurationID, 200);
            }
            else {
                return new RemoveMachineConfigurationResponse(404, "Machine configuration with name: '" + machineConfigurationID + "' could not be found.");
            }
        } catch (Exception e) {
            return new RemoveMachineConfigurationResponse(400, "Unable to remove machine configuration: " + e);
        }
    }
}
