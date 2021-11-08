package lambda;

import CreateClassification.*;
import db.ClassificationDAO;
import entities.Classification;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CreateClassificationTest extends LambdaTest {

    Classification classification;
    ClassificationDAO dao;
    CreateClassificationHandler ccHandler;
    CreateClassificationRequest req;
    CreateClassificationRequest reqWithParent;

    @Before
    public void setup() {
        dao = mock(ClassificationDAO.class);
        classification = new Classification("createTest");
        ccHandler = new CreateClassificationHandler(dao);
        req = new CreateClassificationRequest("createTest", null);
        reqWithParent = new CreateClassificationRequest("childTest", "createTest");
    }

    @Test
    public void testCreateClassification() throws SQLException {
        // add the "child" and make sure we get the correct response
        when(dao.createClassification("childTest", "createTest")).thenReturn(true);
        CreateClassificationResponse handleResult = ccHandler.handle(reqWithParent);
        assertEquals(handleResult.response, "childTest,createTest");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testCreateClassification_NullParent() throws SQLException {
        // add the classification with a null parent and make sure we get the correct response
        when(dao.createClassification("createTest", null)).thenReturn(true);
        CreateClassificationResponse handleResult = ccHandler.handle(req);
        assertEquals(handleResult.response, "createTest,null");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testFailCreateClassification() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createClassification("createTest", null)).thenReturn(false);
        CreateClassificationResponse handleResult = ccHandler.handle(req);
        assertEquals(handleResult.response, "createTest");
        assertEquals(handleResult.httpCode, 409);
        assertEquals(handleResult.error, "Classification already exists.");
    }



}
