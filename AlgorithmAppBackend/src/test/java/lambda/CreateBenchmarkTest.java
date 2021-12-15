package lambda;

import CreateBenchmark.CreateBenchmarkHandler;
import CreateBenchmark.CreateBenchmarkRequest;
import CreateBenchmark.CreateBenchmarkResponse;
import db.BenchmarkDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CreateBenchmarkTest extends LambdaTest {

    BenchmarkDAO dao;
    CreateBenchmarkHandler caHandler;
    CreateBenchmarkRequest reqWithParent;
    MockedStatic<UUID>  mockUuidUtil;

    @Before
    public void setup() {
        dao = mock(BenchmarkDAO.class);
        mockUuidUtil= mockStatic(UUID.class);
        UUID mockUUID = mock(UUID.class);
        when(mockUUID.toString()).thenReturn("fake-bm-uuid");
        mockUuidUtil.when(UUID::randomUUID).thenReturn(mockUUID);
        caHandler = new CreateBenchmarkHandler(dao);
        reqWithParent = new CreateBenchmarkRequest("TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID");

    }

    @After
    public void tearDown() {
        // Need to do this to deregister static mocking or else error will occur
        mockUuidUtil.close();
    }

    @Test
    public void testCreateBenchmark() throws SQLException {

        when(dao.createBenchmark("fake-bm-uuid","TestBench", 10000, new Date(2021,11,10), "TestLang", "TestImplName", "TestMachineID", "TestProbID")).thenReturn(true);
        CreateBenchmarkResponse handleResponse = caHandler.handle(reqWithParent);

        CreateBenchmarkResponse expectedResponse = new CreateBenchmarkResponse(
                200,
                "fake-bm-uuid",
                "TestBench",
                10000,
                new Date(2021,11,10),
                "TestLang",
                "TestImplName",
                "TestMachineID",
                "TestProbID"
        );

        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testFailCreateImplementation() throws SQLException {
        // add the Benchmark with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createBenchmark("fake-bm-uuid","TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID")).thenReturn(false);
        CreateBenchmarkResponse handleResponse = caHandler.handle(reqWithParent);

        CreateBenchmarkResponse expectedResponse = new CreateBenchmarkResponse(
                409,
                "Benchmark already exists"
        );
        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testFailDBConnection() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createBenchmark("fake-bm-uuid","TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID")).thenThrow(new NullPointerException());
        CreateBenchmarkResponse handleResponce = caHandler.handle(reqWithParent);
        assertEquals(handleResponce.httpCode, 400);
        assertEquals(handleResponce.error, "Unable to create Benchmark: TestBench with id fake-bm-uuid\n(null)");
    }

    @Test
    public void testRequestAndResponseClasses() {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        CreateBenchmarkRequest req = new CreateBenchmarkRequest();
        req.setBenchmarkName("test1");
        assertEquals(req.getBenchmarkName(), "test1");
        req.setAlgoName("test2");
        assertEquals(req.getAlgoName(), "test2");
        req.setImplName("test3");
        assertEquals(req.getImplName(), "test3");
        req.setMachineConfigName("test4");
        assertEquals(req.getMachineConfigName(), "test4");
        req.setProbInstanceUUID("test5");
        assertEquals(req.getProbInstanceUUID(), "test5");
        req.setTimeToRun(3);
        assertEquals(req.getTimeToRun(), 3);
        req.setDateRun(new Date(1, 1, 1));
        assertEquals(req.getDateRun(), new Date(1, 1, 1));
        assertEquals(req.toString(), "{\"benchmarkName\":\"test1\",\"algoName\":\"test2\",\"machineConfigName\":\"test4\",\"implName\":\"test3\",\"probInstanceUUID\":\"test5\",\"dateRun\":\"Feb 1, 1901\",\"timeToRun\":3}");

        CreateBenchmarkResponse response = new CreateBenchmarkResponse(20, "20", "20", 20, new Date(1,1,1), "20", "20", "20", "20");
        CreateBenchmarkResponse response2 = new CreateBenchmarkResponse(20, "20");
        assertFalse(response2.equals(response));
        assertFalse(response2.equals(new Date(1,2,3)));
        response.setBenchName("test");
        assertEquals(response.getBenchName(), "test");
        response.setBenchID("test1");
        assertEquals(response.getBenchID(), "test1");
        response.setAlgoName("test2");
        assertEquals(response.getAlgoName(), "test2");
        response.setImplName("test3");
        assertEquals(response.getImplName(), "test3");
        response.setMachineConfigName("test4");
        assertEquals(response.getMachineConfigName(), "test4");
        response.setProblemInstanceName("test5");
        assertEquals(response.getProblemInstanceName(), "test5");
        response.setTimeToRun(3);
        assertEquals(response.getTimeToRun(), 3);
        response.setDateRun(new Date(1, 1, 1));
        assertEquals(response.getDateRun(), new Date(1, 1, 1));

        System.out.println(response);
        assertEquals(response.toString(), "{\"httpCode\":20,\"error\":\"\",\"benchID\":\"test1\",\"benchName\":\"test\",\"algoName\":\"test2\",\"machineConfigName\":\"test4\",\"implName\":\"test3\",\"problemInstanceName\":\"test5\",\"dateRun\":\"Feb 1, 1901\",\"timeToRun\":3}");

    }


}
