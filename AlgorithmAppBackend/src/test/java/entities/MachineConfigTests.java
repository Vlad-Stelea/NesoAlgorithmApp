package entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MachineConfigTests {
    @Test
    public void testGetsAndSets() {
        MachineConfiguration machineConfig = new MachineConfiguration("", 2, 2, "", 1);
        String name = "name";
        int l1Cache = 2;
        int l2Cache = 2;
        String chip = "chip";
        int threads = 2;

        machineConfig.setMachineConfigName(name);
        machineConfig.setL1Cache(l1Cache);
        machineConfig.setL2Cache(l2Cache);
        machineConfig.setChip(chip);
        machineConfig.setThreads(threads);

        assertEquals(name, machineConfig.getMachineConfigName());
        assertEquals(l1Cache, machineConfig.getL1Cache());
        assertEquals(l2Cache, machineConfig.getL2Cache());
        assertEquals(chip, machineConfig.getChip());
        assertEquals(threads, machineConfig.getThreads());
    }
}
