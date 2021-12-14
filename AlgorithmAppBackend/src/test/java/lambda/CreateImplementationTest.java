package lambda;

import CreateImplementation.*;
import db.ImplementationDAO;
import entities.Implementation;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CreateImplementationTest extends LambdaTest {

    ImplementationDAO dao;
    CreateImplementationHandler caHandler;
    CreateImplementationRequest reqWithParent;
    IImplementationStorage storage;



    @Before
    public void setup() {
        dao = mock(ImplementationDAO.class);
        storage = mock(IImplementationStorage.class);
        caHandler = new CreateImplementationHandler(dao, storage);
        reqWithParent = new CreateImplementationRequest("childTest","TestCode", ".test", "TestLang", "TestClass");

    }

    @Test
    public void testCreateImplementation() throws SQLException {
        // Mock the dao and storage classes
        when(dao.hasImplementation("childTest", "TestClass")).thenReturn(false);
        when(dao.createImplementation(any(), any(), any(), any())).thenReturn(true);
        when(storage.storeImplementation(any(), any())).thenReturn("www.fakeurl.com");

        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        CreateImplementationResponse expectedResponse = new CreateImplementationResponse(
                200,
                "childTest",
                "TestClass",
                "www.fakeurl.com",
                "TestLang"
        );

        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testAlreadyHaveImplementation() throws SQLException {
        // Mock the dao and storage classes
        when(dao.hasImplementation("childTest", "TestClass")).thenReturn(true);
        when(dao.createImplementation(any(), any(), any(), any())).thenReturn(true);
        when(storage.storeImplementation(any(), any())).thenReturn("www.fakeurl.com");

        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        CreateImplementationResponse expectedResponse = new CreateImplementationResponse(
                409,
                "Implementation already exists"
        );
        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testFailCreateImplementation() throws SQLException {
        // add the implementation with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createImplementation("childTest", "TestCode","TestLang", "TestClass")).thenReturn(false);
        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        CreateImplementationResponse expectedResponse = new CreateImplementationResponse(
                409,
                "Implementation already exists"
        );
        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testNoDBConnection() throws SQLException {
        // add the implementation with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.hasImplementation("childTest", "TestClass")).thenThrow(new NullPointerException());
        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        CreateImplementationResponse expectedResponse = new CreateImplementationResponse(
                400,
                "Unknown Error"
        );
        assertEquals(expectedResponse, handleResponse);
    }

    @Test
    public void testRequestAndResponceClasses() throws SQLException {
        // add the implementation with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        CreateImplementationRequest req = new CreateImplementationRequest();
        req.setAlgoName("test");
        assertEquals(req.getAlgoName(),"test");
        req.setCode("test1");
        assertEquals(req.getCode(),"test1");
        req.setImplName("test2");
        assertEquals(req.getImplName(),"test2");
        req.setLanguage("test3");
        assertEquals(req.getLanguage(),"test3");
        req.setFileExtension("test4");
        assertEquals(req.getFileExtension(),"test4");

        assertEquals(req.toString(), "{\"implName\":\"test2\",\"algoName\":\"test\",\"code\":\"test1\",\"fileExtension\":\"test4\",\"language\":\"test3\"}");

        CreateImplementationResponse response = new CreateImplementationResponse(20, "d");
        assertFalse(response.equals(dao));
        response.setAlgoName("test");
        assertEquals(response.getAlgoName(), "test");
        response.setCodeUrl("test1");
        assertEquals(response.getCodeUrl(), "test1");
        response.setError("test2");
        assertEquals(response.getError(), "test2");
        response.setImplName("test3");
        assertEquals(response.getImplName(), "test3");
        response.setLanguage("test4");
        assertEquals(response.getLanguage(), "test4");
        response.setStatusCode(123);
        assertEquals(response.getStatusCode(), 123);

        assertEquals(response.toString(), "{\"statusCode\":123,\"implName\":\"test3\",\"algoName\":\"test\",\"codeUrl\":\"test1\",\"language\":\"test4\",\"error\":\"test2\"}");


    }



}
