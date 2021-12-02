package CreateMachineConfiguration;

import com.google.gson.Gson;

import java.util.Objects;

public class CreateMachineConfigurationResponse {

    String machineConfigName;
    String machineConfigUUID;
    int L1Cache;
    int L2Cache;
    String chip;
    int threads;

    int httpCode;
    String error;

    public CreateMachineConfigurationResponse(String machineConfigName, String machineConfigUUID, int l1Cache, int l2Cache, String chip, int threads, int httpCode) {
        this.machineConfigName = machineConfigName;
        this.machineConfigUUID = machineConfigUUID;
        L1Cache = l1Cache;
        L2Cache = l2Cache;
        this.chip = chip;
        this.threads = threads;
        this.httpCode = httpCode;
    }

    public CreateMachineConfigurationResponse(int httpCode, String error) {
        this.httpCode = httpCode;
        this.error = error;
    }

    public String getMachineConfigName() { return machineConfigName; }

    public void setMachineConfigName(String machineConfigName) { this.machineConfigName = machineConfigName; }

    public String getMachineConfigUUID() { return machineConfigUUID; }

    public void setMachineConfigUUID(String machineConfigUUID) { this.machineConfigUUID = machineConfigUUID; }

    public int getL1Cache() { return L1Cache; }

    public void setL1Cache(int L1Cache) { this.L1Cache = L1Cache; }

    public int getL2Cache() { return L2Cache; }

    public void setL2Cache(int L2Cache) { this.L2Cache = L2Cache; }

    public String getChip() { return chip; }

    public void setChip(String chip) { this.chip = chip; }

    public int getThreads() { return threads; }

    public void setThreads(int threads) { this.threads = threads; }

    public int getHttpCode() { return httpCode; }

    public void setHttpCode(int httpCode) { this.httpCode = httpCode; }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMachineConfigurationResponse that = (CreateMachineConfigurationResponse) o;
        return L1Cache == that.L1Cache && L2Cache == that.L2Cache && threads == that.threads && Objects.equals(machineConfigName, that.machineConfigName) && machineConfigUUID.equals(that.machineConfigUUID) && Objects.equals(chip, that.chip);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
