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
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> ccEvent = new CreateClassificationEvent<>(reqWithParent, new CreateClassificationResponse("", 400));
        when(dao.createClassification("childTest", "createTest")).thenReturn(true);
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> handleResult = ccHandler.handle(ccEvent);
        assertEquals(handleResult.getResponse().response, "childTest,createTest");
        assertEquals(handleResult.getResponse().httpCode, 200);
    }

    @Test
    public void testCreateClassification_NullParent() throws SQLException {
        // add the classification with a null parent and make sure we get the correct response
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> ccEvent = new CreateClassificationEvent<>(req, new CreateClassificationResponse("", 400));
        when(dao.createClassification("createTest", null)).thenReturn(true);
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> handleResult = ccHandler.handle(ccEvent);
        assertEquals(handleResult.getResponse().response, "createTest,null");
        assertEquals(handleResult.getResponse().httpCode, 200);
    }

    @Test
    public void testFailCreateClassification() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> ccEvent = new CreateClassificationEvent<>(req, new CreateClassificationResponse("", 400));
        when(dao.createClassification("createTest", null)).thenReturn(false);
        CreateClassificationEvent<CreateClassificationRequest, CreateClassificationResponse> handleResult = ccHandler.handle(ccEvent);
        assertEquals(handleResult.getResponse().response, "createTest");
        assertEquals(handleResult.getResponse().httpCode, 409);
        assertEquals(handleResult.getResponse().error, "Classification already exists.");
    }



}
