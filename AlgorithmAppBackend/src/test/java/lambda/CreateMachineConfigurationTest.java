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

    @Test
    public void testFailConnectDB() throws SQLException {
        // mock add the problem instance, simulate a failed response, and make sure the handler responds appropriately
        when(dao.createMachineConfiguration(request.getMachineConfigName(), request.getL1Cache(), request.getL2Cache(), request.getChip(), request.getThreads())).thenThrow(new NullPointerException());
        CreateMachineConfigurationResponse handleResult = handler.handle(request);
        CreateMachineConfigurationResponse expectedResponse = new CreateMachineConfigurationResponse(400, "Unable to create machine configuration.");
        assertEquals(expectedResponse, handleResult);
    }


    @Test
    public void testRequestAndResponseClasses() throws SQLException {
        CreateMachineConfigurationRequest req = new CreateMachineConfigurationRequest();
        req.setChip("test");
        assertEquals(req.getChip(), "test");
        req.setMachineConfigName("test1");
        assertEquals(req.getMachineConfigName(), "test1");
        req.setL1Cache(1);
        assertEquals(req.getL1Cache(), 1);
        req.setL2Cache(2);
        assertEquals(req.getL2Cache(), 2);
        req.setThreads(3);
        assertEquals(req.getThreads(), 3);

        assertEquals(req.toString(), "{\"machineConfigName\":\"test1\",\"L1Cache\":1,\"L2Cache\":2,\"chip\":\"test\",\"threads\":3}");

        CreateMachineConfigurationResponse response = new CreateMachineConfigurationResponse(3, "23");
        response.setChip("test");
        assertEquals(response.getChip(), "test");
        response.setMachineConfigName("test1");
        assertEquals(response.getMachineConfigName(), "test1");
        response.setL1Cache(1);
        assertEquals(response.getL1Cache(), 1);
        response.setL2Cache(2);
        assertEquals(response.getL2Cache(), 2);
        response.setThreads(3);
        assertEquals(response.getThreads(), 3);
        response.setError("123");
        assertEquals(response.getError(), "123");
        response.setHttpCode(123);
        assertEquals(response.getHttpCode(), 123);

        assertEquals(response.toString(), "{\"machineConfigName\":\"test1\",\"L1Cache\":1,\"L2Cache\":2,\"chip\":\"test\",\"threads\":3,\"httpCode\":123,\"error\":\"123\"}");

    }



}
