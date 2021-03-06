package lambda;

import CreateAlgorithm.*;
import db.AlgorithmDAO;
import entities.Algorithm;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CreateAlgorithmTest extends LambdaTest {

    AlgorithmDAO dao;
    CreateAlgorithmHandler caHandler;
    CreateAlgorithmRequest reqWithParent;



    @Before
    public void setup() {
        dao = mock(AlgorithmDAO.class);
        caHandler = new CreateAlgorithmHandler(dao);
        reqWithParent = new CreateAlgorithmRequest("childTest", "TestClass");

    }

    @Test
    public void testCreateAlgorithm() throws SQLException {
        // add the "child" and make sure we get the correct response
        when(dao.createAlgorithm("childTest", "TestClass")).thenReturn(true);
        CreateAlgorithmResponse handleResponce = caHandler.handle(reqWithParent);
        assertEquals(handleResponce.response, "childTest, TestClass");
        assertEquals(handleResponce.httpCode, 200);
    }

    @Test
    public void testFailCreateAlgorithm() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createAlgorithm("childTest", "TestClass")).thenReturn(false);
        CreateAlgorithmResponse handleResponce = caHandler.handle(reqWithParent);
        assertEquals(handleResponce.response, "childTest");
        assertEquals(handleResponce.httpCode, 409);
        assertEquals(handleResponce.error, "Algorithm Already Exists");
    }

    @Test
    public void testFailDBConnection() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createAlgorithm("childTest", "TestClass")).thenThrow(new NullPointerException());
        CreateAlgorithmResponse handleResponce = caHandler.handle(reqWithParent);
        System.out.println(handleResponce);
        assertEquals(handleResponce.response, "Unable to create Algorithm: childTest(null)");
        assertEquals(handleResponce.httpCode, 400);
        assertEquals(handleResponce.error, "");
    }

    @Test
    public void testRequestAndResponseClasses() {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        CreateAlgorithmRequest req = new CreateAlgorithmRequest();
        req.setAlgoName("test");
        assertEquals(req.getAlgoName(), "test");
        req.setClassName("testClass");
        assertEquals(req.getClassName(), "testClass");
        assertEquals(req.toString(), "CreateAlgorithm(test, testClass)");
        CreateAlgorithmResponse response = new CreateAlgorithmResponse("123", 123, "234");
        System.out.println(response);
        assertEquals(response.toString(), "{\"response\":\"123\",\"httpCode\":123,\"error\":\"234\"}");

    }



}
