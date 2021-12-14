package entities;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlgorithmPageTests {
    @Test
    public void testGetsAndSets() {
        AlgorithmPage ap = new AlgorithmPage();
        Algorithm algorithm = new Algorithm(null);
        List<MachineConfiguration> machineConfigs = Arrays.asList(new MachineConfiguration("name", 1,1,"chip",1));
        ap.setAlgorithm(algorithm);
        ap.setMachineConfigurations(machineConfigs);

        assertEquals(algorithm, ap.getAlgorithm());
        assertEquals(machineConfigs, ap.getMachineConfigurations());
    }
}
