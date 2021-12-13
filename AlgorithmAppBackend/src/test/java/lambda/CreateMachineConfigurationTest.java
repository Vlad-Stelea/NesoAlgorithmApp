package lambda;

import CreateMachineConfiguration.CreateMachineConfigurationHandler;
import CreateMachineConfiguration.CreateMachineConfigurationRequest;
import CreateMachineConfiguration.CreateMachineConfigurationResponse;
import db.MachineConfigurationDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CreateMachineConfigurationTest {

    MachineConfigurationDAO dao;
    CreateMachineConfigurationHandler handler;
    CreateMachineConfigurationRequest request;

    @Before
    public void setup() {
        dao = mock(MachineConfigurationDAO.class);
        handler = new CreateMachineConfigurationHandler(dao);
        request = new CreateMachineConfigurationRequest("cmc_test", 123, 456, "cmc_chip", 10);
    }

    @Test
    public void testCreateMachineConfiguration() throws SQLException {
        // mock add the machine config and see if we get the right response
        when(dao.createMachineConfiguration(request.getMachineConfigName(), request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads())).thenReturn(true);
        CreateMachineConfigurationResponse handleResult = handler.handle(request);
        CreateMachineConfigurationResponse expectedResponse = new CreateMachineConfigurationResponse(request.getMachineConfigName(), request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads(), 200);
        assertEquals(expectedResponse, handleResult);
    }

    @Test
    public void testFailCreateMachineConfiguration() throws SQLException {
        // mock add the problem instance, simulate a failed response, and make sure the handler responds appropriately
        when(dao.createMachineConfiguration(request.getMachineConfigName(), request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads())).thenReturn(false);
        CreateMachineConfigurationResponse handleResult = handler.handle(request);
        CreateMachineConfigurationResponse expectedResponse = new CreateMachineConfigurationResponse(409, "Machine configuration with UUID: '" + request.getMachineConfigName() + "' already exists.");
        assertEquals(expectedResponse, handleResult);
    }

}
