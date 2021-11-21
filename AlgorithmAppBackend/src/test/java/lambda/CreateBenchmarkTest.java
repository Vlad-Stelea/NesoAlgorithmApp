package lambda;

import CreateBenchmark.CreateBenchmarkHandler;
import CreateBenchmark.CreateBenchmarkRequest;
import CreateBenchmark.CreateBenchmarkResponse;
import db.BenchmarkDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateBenchmarkTest extends LambdaTest {

    BenchmarkDAO dao;
    CreateBenchmarkHandler caHandler;
    CreateBenchmarkRequest reqWithParent;




    @Before
    public void setup() {
        dao = mock(BenchmarkDAO.class);

        caHandler = new CreateBenchmarkHandler(dao);
        reqWithParent = new CreateBenchmarkRequest("1231232","TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID");

    }

    @Test
    public void testCreateBenchmark() throws SQLException {

        when(dao.createBenchmark("1231232","TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID")).thenReturn(true);

        CreateBenchmarkResponse handleResponse = caHandler.handle(reqWithParent);

        CreateBenchmarkResponse expectedResponse = new CreateBenchmarkResponse(
                200,
                "1231232",
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
        when(dao.createBenchmark("1231232","TestBench",10000,new Date(2021,11,10), "TestLang","TestImplName","TestMachineID", "TestProbID")).thenReturn(false);
        CreateBenchmarkResponse handleResponse = caHandler.handle(reqWithParent);

        CreateBenchmarkResponse expectedResponse = new CreateBenchmarkResponse(
                409,
                "Benchmark already exists"
        );
        assertEquals(expectedResponse, handleResponse);
    }


}
