package CreateMachineConfiguration;

import db.MachineConfigurationDAO;

import java.util.UUID;

public class CreateMachineConfigurationHandler {

    MachineConfigurationDAO dao;

    public CreateMachineConfigurationHandler(MachineConfigurationDAO dao) {
        this.dao = dao;
    }

    public CreateMachineConfigurationResponse handle(CreateMachineConfigurationRequest request) {
        try {
            String uuid = UUID.randomUUID().toString();
            String machineConfigName = request.getMachineConfigName();
            int L1Cache = request.getL1Cache();
            int L2Cache = request.getL2Cache();
            String chip = request.getChip();
            int threads = request.getThreads();

            if(dao.createMachineConfiguration(machineConfigName, uuid, L1Cache, L2Cache, chip, threads)) {
                return new CreateMachineConfigurationResponse(machineConfigName, uuid, L1Cache, L2Cache, chip, threads, 200);
            }
            else {
                return new CreateMachineConfigurationResponse(409, "Machine configuration with UUID: '" + uuid + "' already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateMachineConfigurationResponse(400, "Unable to create machine configuration.");
        }
    }

}
