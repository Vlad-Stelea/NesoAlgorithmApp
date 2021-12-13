package lambda;

import GetUserActions.GetUserActionsHandler;
import GetUserActions.GetUserActionsResponse;
import ReclassifyAlgorithm.ReclassifyAlgorithmHandler;
import ReclassifyAlgorithm.ReclassifyAlgorithmRequest;
import db.ActivityLogDAO;
import db.AlgorithmDAO;
import entities.UserAction;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserActionTest {

     private ArrayList<UserAction> action = new ArrayList<>();;
     private ActivityLogDAO dao;
     private GetUserActionsHandler rcHandler;
     private UserAction  test1;
     private UserAction  test2;
     private UserAction  test3;
     private UserAction  test4;

     @Before
     public void setup() {
          dao = mock(ActivityLogDAO.class);
          test1 = new UserAction("1234","jack","create benchmark",new Date(1, 2 ,3));
          test2 = new UserAction("1235","jill","create classification",new Date(1, 2 ,3));
          test3 = new UserAction("1236","jacob","create algorithm",new Date(1, 2 ,3));
          test4 = new UserAction("1237","jon","create implementation",new Date(1, 2 ,3));
          action.add(test1);
          action.add(test2);
          action.add(test3);
          action.add(test4);
          rcHandler = new GetUserActionsHandler(dao);

     }

     @Test
     public void GetAllUserActionsTest() throws SQLException {
          when(dao.getAllUserAction()).thenReturn(action);
          GetUserActionsResponse response = rcHandler.handle();
          assertEquals(response.getUserActionList(), action);

     }
}
