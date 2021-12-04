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
    MockedStatic<UUID> mockUuidUtil;
    final String UUIDString = "Fake-mc-UUID";

    @Before
    public void setup() {
        dao = mock(MachineConfigurationDAO.class);
        handler = new CreateMachineConfigurationHandler(dao);
        mockUuidUtil = mockStatic(UUID.class);
        UUID mockUUID = mock(UUID.class);
        when(mockUUID.toString()).thenReturn(UUIDString);
        mockUuidUtil.when(UUID::randomUUID).thenReturn(mockUUID);
        request = new CreateMachineConfigurationRequest("cmc_test", 123, 456, "cmc_chip", 10);
    }

    @After
    public void tearDown() {
        // Need to do this to deregister static mocking or else error will occur
        mockUuidUtil.close();
    }

    @Test
    public void testCreateMachineConfiguration() throws SQLException {
        // mock add the machine config and see if we get the right response
        when(dao.createMachineConfiguration(request.getMachineConfigName(), UUIDString, request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads())).thenReturn(true);
        CreateMachineConfigurationResponse handleResult = handler.handle(request);
        CreateMachineConfigurationResponse expectedResponse = new CreateMachineConfigurationResponse(request.getMachineConfigName(), UUIDString, request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads(), 200);
        assertEquals(expectedResponse, handleResult);
    }

    @Test
    public void testFailCreateMachineConfiguration() throws SQLException {
        // mock add the problem instance, simulate a failed response, and make sure the handler responds appropriately
        when(dao.createMachineConfiguration(request.getMachineConfigName(), UUIDString, request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads())).thenReturn(false);
        CreateMachineConfigurationResponse handleResult = handler.handle(request);
        CreateMachineConfigurationResponse expectedResponse = new CreateMachineConfigurationResponse(409, "Machine configuration with UUID: '" + UUIDString + "' already exists.");
        assertEquals(expectedResponse, handleResult);
    }

}
