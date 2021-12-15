package entities;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserActionTests {

    @Test
    public void testCreation() {
        String actLogUuid = "test";
        String username = "username";
        String action = "action";
        Date date = new Date(1);
        UserAction userAction = new UserAction(actLogUuid, username, action, date);

        assertEquals(actLogUuid, userAction.getActivityLogUUID());
        assertEquals(username, userAction.getUsername());
        assertEquals(action, userAction.getAction());
        assertEquals(date, userAction.getDate());
    }
}
