package lambda;

import GetUserActions.GetUserActions;
import GetUserActions.GetUserActionsHandler;
import GetUserActions.GetUserActionsRequest;
import GetUserActions.GetUserActionsResponse;
import com.amazonaws.services.lambda.runtime.Context;
import db.ActivityLogDAO;
import entities.UserAction;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserActionTest {

     private ArrayList<UserAction> action = new ArrayList<>();;
     private ActivityLogDAO dao;
     private GetUserActionsHandler rcHandler;
     private GetUserActionsRequest rcRequest;
     private UserAction  test1;
     private UserAction  test2;
     private UserAction  test3;
     private UserAction  test4;

     @Before
     public void setup() {
          dao = mock(ActivityLogDAO.class);
          test1 = new UserAction("1234","jack","create benchmark",new Date(1, 2 ,3));
          test2 = new UserAction("1235","jack","create classification",new Date(1, 2 ,3));
          test3 = new UserAction("1236","jacob","create algorithm",new Date(1, 2 ,3));
          test4 = new UserAction("1237","jon","create implementation",new Date(1, 2 ,3));
          action.add(test1);
          action.add(test2);
          rcHandler = new GetUserActionsHandler(dao);
          rcRequest = new GetUserActionsRequest("jack");
     }

     @Test
     public void testRequestClass() {
          GetUserActionsRequest req = new GetUserActionsRequest();
          req.setUsername("helloWorld");

          GetUserActionsRequest anotherReq = new GetUserActionsRequest("helloWorld");
          assertEquals(req.getUsername(), anotherReq.getUsername());
          assertEquals(req.toString(), anotherReq.toString());
     }

     @Test
     public void GetAllUserActionsTest() throws SQLException {
          when(dao.getUserAction("jack")).thenReturn(action);

          GetUserActionsResponse response = rcHandler.handle(rcRequest);
          assertEquals(response.getUserActionList(), action);
     }

     @Test
     public void testMainGetAllUserActions() throws SQLException {
          // mock the main lambda function
          GetUserActionsHandler mockHandler = mock(GetUserActionsHandler.class);
          GetUserActions mainLambda = new GetUserActions(mockHandler);
          GetUserActionsRequest mockRequest = new GetUserActionsRequest("some_username");
          Context mockContext = new TestContext();

          GetUserActionsResponse mockResponse = new GetUserActionsResponse(new ArrayList<>(), "some_username", 200);
          when(mainLambda.handleRequest(mockRequest, mockContext)).thenReturn(mockResponse);
          when(dao.getUserAction("some_username")).thenReturn(new ArrayList<>());
          GetUserActionsResponse receivedResponse = mainLambda.handleRequest(mockRequest, mockContext);

          assertEquals(mockResponse, receivedResponse);
          assertEquals(mockResponse.toString(), receivedResponse.toString());
     }

     @Test
     public void testFailGetAllUserActions() throws SQLException {
          when(dao.getUserAction("jack")).thenThrow(NullPointerException.class);

          GetUserActionsResponse expectedResponse = new GetUserActionsResponse(400, "Unable to get userActions\n");
          GetUserActionsResponse actualResponse = rcHandler.handle(rcRequest);

          assertTrue(actualResponse.getError().contains(expectedResponse.getError()));
          assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
     }
}
