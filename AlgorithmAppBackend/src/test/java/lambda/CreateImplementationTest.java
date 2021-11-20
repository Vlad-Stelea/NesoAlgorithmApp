package lambda;

import CreateImplementation.*;
import db.ImplementationDAO;
import entities.Implementation;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
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
        reqWithParent = new CreateImplementationRequest("childTest","TestCode", "TestLang", "TestClass");

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



}
