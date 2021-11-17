package entities;

public class MachineConfiguration {

    private String machineConfigName;
    private String machineConfigUUID;
    private int L1Cache;
    private int L2Cache;
    private String chip;
    private int threads;

    public MachineConfiguration(String machineConfigName, String machineConfigUUID, int l1Cache, int l2Cache, String chip, int threads) {
        this.machineConfigName = machineConfigName;
        this.machineConfigUUID = machineConfigUUID;
        L1Cache = l1Cache;
        L2Cache = l2Cache;
        this.chip = chip;
        this.threads = threads;
    }

    public String getMachineConfigName() { return machineConfigName; }
    public void setMachineConfigName(String machineConfigName) { this.machineConfigName = machineConfigName; }

    public String getMachineConfigUUID() { return machineConfigUUID; }
    public void setMachineConfigUUID(String machineConfigUUID) { this.machineConfigUUID = machineConfigUUID; }

    public int getL1Cache() { return L1Cache; }
    public void setL1Cache(int l1Cache) { L1Cache = l1Cache; }

    public int getL2Cache() { return L2Cache; }
    public void setL2Cache(int l2Cache) { L2Cache = l2Cache; }

    public String getChip() { return chip; }
    public void setChip(String chip) { this.chip = chip; }

    public int getThreads() { return threads; }
    public void setThreads(int threads) { this.threads = threads; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MachineConfiguration mc = (MachineConfiguration) o;
        return machineConfigUUID.equals(mc.machineConfigUUID);
    }

}
