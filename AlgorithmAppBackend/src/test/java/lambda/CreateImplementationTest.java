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
        // add the "child" and make sure we get the correct response
        when(dao.createImplementation("childTest", "TestCode","TestLang", "TestClass")).thenReturn(true);
        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);

        assertEquals(handleResponse.response, "childTest,TestCode,TestLang,TestClass");
        assertEquals(handleResponse.httpCode, 200);
    }

    @Test
    public void testFailCreateImplementation() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
        when(dao.createImplementation("childTest", "TestCode","TestLang", "TestClass")).thenReturn(false);
        CreateImplementationResponse handleResponse = caHandler.handle(reqWithParent);
        assertEquals(handleResponse.response, "childTest");
        assertEquals(handleResponse.httpCode, 409);
        assertEquals(handleResponse.error, "Implementation already exists.");
    }



}
