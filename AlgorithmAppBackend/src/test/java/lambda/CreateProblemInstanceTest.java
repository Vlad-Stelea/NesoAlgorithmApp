package lambda;

import CreateProblemInstance.CreateProblemInstanceHandler;
import CreateProblemInstance.CreateProblemInstanceRequest;
import CreateProblemInstance.CreateProblemInstanceResponse;
import CreateProblemInstance.IProblemInstanceStorage;
import db.ProblemInstanceDAO;
import entities.ProblemInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateProblemInstanceTest extends LambdaTest {

    ProblemInstance probInstance;
    ProblemInstanceDAO dao;
    IProblemInstanceStorage storage;
    CreateProblemInstanceHandler cpiHandler;
    CreateProblemInstanceRequest req;
    MockedStatic<UUID> mockUidUtil;

    @Before
    public void setup() {
        dao = mock(ProblemInstanceDAO.class);
        storage = mock(IProblemInstanceStorage.class);
        mockUidUtil = mockStatic(UUID.class);
        UUID mockUUID = mock(UUID.class);
        when(mockUUID.toString()).thenReturn("Fake-uuid");
        mockUidUtil.when(UUID::randomUUID).thenReturn(mockUUID);
        probInstance = new ProblemInstance("cpi_test", "cpiAlgo_test");
        cpiHandler = new CreateProblemInstanceHandler(dao, storage);
        req = new CreateProblemInstanceRequest("cpi_test", "cpi_payload_test", ".txt", "cpiAlgo_test");
    }

    @After
    public void tearDown() {
        // Need to do this to deregister static mocking or else error will occur
        mockUidUtil.close();
    }

    @Test
    public void testCreateProblemInstance() throws SQLException {
        // add the problem instance and make sure we get the correct response
        when(dao.createProblemInstance("Fake-uuid", "cpi_test", "fake_url.txt", "cpiAlgo_test")).thenReturn(true);
        when(storage.storeProblemInstance(any(), eq(".txt"))).thenReturn("fake_url.txt");
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        CreateProblemInstanceResponse expectedResponse = new CreateProblemInstanceResponse("Fake-uuid", "cpi_test", "fake_url.txt", "cpiAlgo_test", 200);
        assertEquals(expectedResponse, handleResult);
    }

    @Test
    public void testFailCreateProblemInstance() throws SQLException {
        // add the problem instance, mock that the problem instance was added already, and ensure the handler responds appropriately
        when(dao.createProblemInstance("Fake-uuid", "cpi_test", "fake_url.txt", "cpiAlgo_test")).thenReturn(false);
        when(storage.storeProblemInstance(any(), eq(".txt"))).thenReturn("fake_url.txt");
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        CreateProblemInstanceResponse expectedResponse = new CreateProblemInstanceResponse(409, "Problem Instance already exists");
        assertEquals(expectedResponse, handleResult);
    }

    @Test
    public void testFailDBConnection() throws SQLException {
        // add the problem instance, mock that the problem instance was added already, and ensure the handler responds appropriately
        when(dao.createProblemInstance("Fake-uuid", "cpi_test", "fake_url.txt", "cpiAlgo_test")).thenThrow(NullPointerException.class);
        when(storage.storeProblemInstance(any(), eq(".txt"))).thenReturn("fake_url.txt");
        CreateProblemInstanceResponse handleResult = cpiHandler.handle(req);
        CreateProblemInstanceResponse expectedResponse = new CreateProblemInstanceResponse(400, "Unable to create problem instance");
        assertEquals(expectedResponse, handleResult);
    }

    @Test
    public void testResponseAndRequestClasses() throws SQLException {
        CreateProblemInstanceRequest req = new CreateProblemInstanceRequest();
        req.setAlgoName("test");
        assertEquals(req.getAlgoName(), "test");
        req.setProbInstanceName("test1");
        assertEquals(req.getProbInstanceName(), "test1");
        req.setDatasetPayload("test2");
        assertEquals(req.getDatasetPayload(), "test2");
        req.setFileExtension("test3");
        assertEquals(req.getFileExtension(), "test3");

        assertEquals(req.toString(), "{\"probInstanceName\":\"test1\",\"datasetPayload\":\"test2\",\"fileExtension\":\"test3\",\"algoName\":\"test\"}");

        CreateProblemInstanceResponse response = new CreateProblemInstanceResponse(2, "we");
        response.setAlgorithmName("test");
        assertEquals(response.getAlgorithmName(), "test");
        response.setInstanceId("test1");
        assertEquals(response.getInstanceId(), "test1");
        response.setDatasetUrl("test2");
        assertEquals(response.getDatasetUrl(), "test2");
        response.setError("test3");
        assertEquals(response.getError(), "test3");
        response.setInstanceName("test4");
        assertEquals(response.getInstanceName(), "test4");
        response.setHttpCode(123);
        assertEquals(response.getHttpCode(), 123);

        assertEquals(response.toString(), "{\"instanceId\":\"test1\",\"instanceName\":\"test4\",\"datasetUrl\":\"test2\",\"algorithmName\":\"test\",\"httpCode\":123,\"error\":\"test3\"}");
    }



}
