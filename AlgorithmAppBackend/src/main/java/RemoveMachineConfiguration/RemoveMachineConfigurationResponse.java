package RemoveMachineConfiguration;

import com.google.gson.Gson;

import java.util.Objects;

public class RemoveMachineConfigurationResponse {

    String machineConfigurationID;
    int httpCode;
    String error;

    public RemoveMachineConfigurationResponse(String machineConfigurationID, int httpCode) {
        this.machineConfigurationID = machineConfigurationID;
        this.httpCode = httpCode;
    }

    public RemoveMachineConfigurationResponse(int httpCode, String error) {
        this.httpCode = httpCode;
        this.error = error;
    }

    public String getMachineConfigurationID() { return machineConfigurationID; }

    public void setMachineConfigurationID(String machineConfigurationID) { this.machineConfigurationID = machineConfigurationID; }

    public int getHttpCode() { return httpCode; }

    public void setHttpCode(int httpCode) { this.httpCode = httpCode; }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveMachineConfigurationResponse that = (RemoveMachineConfigurationResponse) o;
        return Objects.equals(machineConfigurationID, that.machineConfigurationID);
    }

}
