package lambda;

import CreateImplementation.*;
import db.ImplementationDAO;
import entities.Implementation;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CreateImplementationTest extends LambdaTest {

    ImplementationDAO dao;
    CreateImplementationHandler caHandler;
    CreateImplementationRequest reqWithParent;



    @Before
    public void setup() {
        dao = mock(ImplementationDAO.class);
        caHandler = new CreateImplementationHandler(dao);
        reqWithParent = new CreateImplementationRequest("childTest","TestCode", "TestLang", "TestClass");

    }

    @Test
    public void testCreateImplementation() throws SQLException {
        // Creates a new implementation and tests that it is made
        when(dao.createImplementation("childTest", "TestCode","TestLang", "TestClass")).thenReturn(true);
        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        CreateImplementationResponse expectedResponse = new CreateImplementationResponse(
                200,
                "childTest",
                "TestClass",
                "TestCode",
                "TestLang"
        );

        assertEquals(handleResponse, expectedResponse);
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
