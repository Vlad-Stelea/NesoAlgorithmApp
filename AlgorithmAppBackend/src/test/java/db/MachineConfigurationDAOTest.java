package db;

import entities.MachineConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineConfigurationDAOTest {

    static MachineConfigurationDAO dao;

    @BeforeClass
    public static void setup() {
        dao = new MachineConfigurationDAO();
    }

    @Test
    public void testGetMachineConfigurations() throws SQLException {
        dao.createMachineConfiguration("machineConfigTest1Name", 123, 456, "tortilla", 10);
        dao.createMachineConfiguration("machineConfigTest2Name", 789, 101112, "potato", 11);

        List<MachineConfiguration> allMCs = dao.getAllMachineConfigurations();
        MachineConfiguration expectedMC1 = new MachineConfiguration("machineConfigTest1Name", 123, 456, "tortilla", 10);
        MachineConfiguration expectedMC2 = new MachineConfiguration("machineConfigTest2Name", 789, 101112, "potato", 11);
        assertTrue(allMCs.contains(expectedMC1));
        assertTrue(allMCs.contains(expectedMC2));

        dao.removeMachineConfiguration("machineConfigTest1Name");
        dao.removeMachineConfiguration("machineConfigTest2Name");

    }

}
