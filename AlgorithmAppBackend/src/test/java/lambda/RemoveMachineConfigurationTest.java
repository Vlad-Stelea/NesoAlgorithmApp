package lambda;


import RemoveMachineConfiguration.RemoveMachineConfigurationHandler;
import RemoveMachineConfiguration.RemoveMachineConfigurationRequest;
import RemoveMachineConfiguration.RemoveMachineConfigurationResponse;
import db.BenchmarkDAO;
import db.MachineConfigurationDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveMachineConfigurationTest {

    MachineConfigurationDAO machineConfigDAO;
    RemoveMachineConfigurationHandler rmcHandler;
    RemoveMachineConfigurationRequest req;
    final String mcUUID = "rmc_uuid_test";

    @Before
    public void setup() {
        machineConfigDAO = mock(MachineConfigurationDAO.class);
        rmcHandler = new RemoveMachineConfigurationHandler(machineConfigDAO);
        req = new RemoveMachineConfigurationRequest(mcUUID);
    }

    @Test
    public void testRemoveMachineConfiguration() throws SQLException {
        // mock that we were able to remove the machine configuration, then check that we get the correct response
        when(machineConfigDAO.removeMachineConfiguration(mcUUID)).thenReturn(true);
        RemoveMachineConfigurationResponse handleResult = rmcHandler.handle(req);
        RemoveMachineConfigurationResponse actualResponse = new RemoveMachineConfigurationResponse(mcUUID, 200);
        assertEquals(handleResult, actualResponse);
    }

    @Test
    public void testFailRemoveMachineConfiguration() throws SQLException {
        // mock that we failed to remove the machine config and check that we handle failure correctly
        when(machineConfigDAO.removeMachineConfiguration(mcUUID)).thenReturn(false);
        RemoveMachineConfigurationResponse handleResult = rmcHandler.handle(req);
        RemoveMachineConfigurationResponse actualResponse = new RemoveMachineConfigurationResponse(404, "Machine configuration with UUID: '" + mcUUID + "' could not be found.");
        assertEquals(handleResult, actualResponse);
    }

}
