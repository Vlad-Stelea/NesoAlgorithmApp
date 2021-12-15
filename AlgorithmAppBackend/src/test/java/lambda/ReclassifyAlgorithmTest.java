package lambda;

import entities.Algorithm;
import entities.Classification;

import ReclassifyAlgorithm.*;
import db.AlgorithmDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReclassifyAlgorithmTest {

    String algoName;
    String className;
    AlgorithmDAO dao;
    ReclassifyAlgorithmHandler rcHandler;
    ReclassifyAlgorithmRequest rcRequest;

    @Before
    public void setup() {
        dao = mock(AlgorithmDAO.class);
        className = "testReclassify_class";
        algoName = "testReclassify_algo";
        rcHandler = new ReclassifyAlgorithmHandler(dao);
        rcRequest = new ReclassifyAlgorithmRequest(algoName, className);
    }

    @Test
    public void testReclassifyAlgorithm() throws SQLException {
        when(dao.reclassifyAlgorithm(algoName, className)).thenReturn(true);
        ReclassifyAlgorithmResponse handleResult = rcHandler.handle(rcRequest);
        assertEquals(handleResult.response, "testReclassify_algo,testReclassify_class");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testFailReclassifyAlgorithm() throws SQLException {
        when(dao.reclassifyAlgorithm(algoName, className)).thenReturn(false);
        ReclassifyAlgorithmResponse handleResult = rcHandler.handle(rcRequest);
        assertEquals(handleResult.response, "testReclassify_algo (testReclassify_class)");
        assertEquals(handleResult.httpCode, 404);
        assertEquals(handleResult.error, "Classification does not exist.");
    }

    @Test
    public void testFailDBConnection() throws SQLException {
        when(dao.reclassifyAlgorithm(algoName, className)).thenThrow(new NullPointerException());
        ReclassifyAlgorithmResponse handleResult = rcHandler.handle(rcRequest);
        assertEquals(handleResult.response, "Unable to reclassify Algorithm: testReclassify_algo (testReclassify_class)\n" +
                "(null)");
        assertEquals(handleResult.httpCode, 400);
        assertEquals(handleResult.error, "");
    }

    @Test
    public void testResponseAndRequestClasses() throws SQLException {
        ReclassifyAlgorithmRequest req = new ReclassifyAlgorithmRequest();
        req.setAlgoName("test1");
        req.setNewClassName("test2");
        assertEquals(req.toString(), "ReclassifyAlgorithm(test1,test2)");

        ReclassifyAlgorithmResponse response = new ReclassifyAlgorithmResponse("123", 20);
        assertEquals(response.toString(), "{\"response\":\"123\",\"httpCode\":20,\"error\":\"\"}");
    }

}
