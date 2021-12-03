package CreateMachineConfiguration;

import com.google.gson.Gson;

public class CreateMachineConfigurationRequest {

    String machineConfigName;
    int L1Cache;
    int L2Cache;
    String chip;
    int threads;

    public CreateMachineConfigurationRequest() {}

    public CreateMachineConfigurationRequest(String machineConfigName, int L1Cache, int L2Cache, String chip, int threads) {
        this.machineConfigName = machineConfigName;
        this.L1Cache = L1Cache;
        this.L2Cache = L2Cache;
        this.chip = chip;
        this.threads = threads;
    }

    public String getMachineConfigName() { return machineConfigName; }

    public void setMachineConfigName(String machineConfigName) { this.machineConfigName = machineConfigName; }

    public int getL1Cache() { return L1Cache; }

    public void setL1Cache(int L1Cache) { this.L1Cache = L1Cache; }

    public int getL2Cache() { return L2Cache; }

    public void setL2Cache(int L2Cache) { this.L2Cache = L2Cache; }

    public String getChip() { return chip; }

    public void setChip(String chip) { this.chip = chip; }

    public int getThreads() { return threads; }

    public void setThreads(int threads) { this.threads = threads; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
