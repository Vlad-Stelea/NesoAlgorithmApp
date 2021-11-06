package CognitoAutoConfirm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CognitoAutoConfirmRequestTest {
    CognitoAutoConfirmRequest request;

    @Before
    public void setup() {
        request = new CognitoAutoConfirmRequest();
    }

    @Test
    public void testUserAttributes() {
        Map<String, String> userAttributes = new HashMap<String, String>();
        userAttributes.put("test", "test");
        request.setUserAttributes(userAttributes);
        assertEquals(request.getUserAttributes(), userAttributes);
    }
}
