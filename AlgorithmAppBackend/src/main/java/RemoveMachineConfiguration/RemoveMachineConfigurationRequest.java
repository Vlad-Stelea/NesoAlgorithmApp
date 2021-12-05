package RemoveMachineConfiguration;

import com.google.gson.Gson;

public class RemoveMachineConfigurationRequest {

    String machineConfigurationID;

    public RemoveMachineConfigurationRequest() {}

    public RemoveMachineConfigurationRequest(String machineConfigurationID) {
        this.machineConfigurationID = machineConfigurationID;
    }

    public String getMachineConfigurationID() { return machineConfigurationID; }

    public void setMachineConfigurationID(String machineConfigurationID) { this.machineConfigurationID = machineConfigurationID; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
