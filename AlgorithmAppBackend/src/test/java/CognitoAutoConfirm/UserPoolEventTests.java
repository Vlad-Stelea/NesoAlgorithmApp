package CognitoAutoConfirm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserPoolEventTests {
    // Use booleans because
    private UserPoolEvent<Boolean, Boolean> event;

    @Before
    public void setup() {
        event = new UserPoolEvent<Boolean, Boolean>(false, false);
    }

    @Test
    public void testEmptyConstructor() {
        new UserPoolEvent<>();
    }

    @Test
    public void testVersion() {
        String version = "1.0.0";
        event.setVersion(version);
        assertEquals(event.getVersion(), version);
    }

    @Test
    public void testRegion() {
        String region = "us-east";
        event.setRegion(region);
        assertEquals(event.getRegion(), region);
    }

    @Test
    public void testUserPoolId() {
        String userPoolId = "userpoolid";
        event.setUserPoolId(userPoolId);
        assertEquals(event.getUserPoolId(), userPoolId);
    }

    @Test
    public void testUserName() {
        String userName = "userName";
        event.setUserName(userName);
        assertEquals(event.getUserName(), userName);
    }

    @Test
    public void testTriggerSource() {
        String triggerSource = "TriggerSource";
        event.setTriggerSource(triggerSource);
        assertEquals(event.getTriggerSource(), triggerSource);
    }

    @Test
    public void testRequest() {
        boolean request = true;
        event.setRequest(request);
        assertTrue(event.getRequest());
    }

    @Test
    public void testResponse() {
        boolean response = true;
        event.setResponse(response);
        assertTrue(event.getResponse());
    }

    @Test
    public void testCallerContext() {
        UserPoolEvent.CallerContext context = new UserPoolEvent.CallerContext();
        String awsSdkVersion = "awsSdkVersion";
        context.setAwsSdkVersion(awsSdkVersion);
        assertEquals(context.getAwsSdkVersion(), awsSdkVersion);
        String clientId = "clientId";
        context.setClientId(clientId);
        assertEquals(context.getClientId(), clientId);
        event.setCallerContext(context);
        assertEquals(event.getCallerContext(), context);
    }

    @Test
    public void testCallerContextEquals() {
        UserPoolEvent.CallerContext defContext = new UserPoolEvent.CallerContext();
        assertEquals(defContext, defContext);
        String notCallerContext = "notCallerContext";
        assertNotEquals(notCallerContext, defContext);
        defContext.setClientId("test");
        defContext.setAwsSdkVersion("test");
        UserPoolEvent.CallerContext wrongContext = new UserPoolEvent.CallerContext();
        wrongContext.setClientId("test");
        assertNotEquals(defContext, wrongContext);
        wrongContext.setClientId("");
        wrongContext.setAwsSdkVersion("test");
        assertNotEquals(defContext, wrongContext);
    }
}
