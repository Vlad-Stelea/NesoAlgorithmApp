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


}
