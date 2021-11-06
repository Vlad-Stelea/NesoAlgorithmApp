package CognitoAutoConfirm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CognitoAutoConfirmResponseTests {
    private CognitoAutoConfirmResponse response;
    @Before
    public void setup() {
        response = new CognitoAutoConfirmResponse();
    }
    @Test
    public void testAutoConfirmUser() {
        response.setAutoConfirmUser(true);
        assertTrue(response.isAutoConfirmUser());
    }

    @Test
    public void testVerifyEmail() {
        response.setAutoVerifyEmail(true);
        assertTrue(response.isAutoVerifyEmail());
    }

    @Test
    public void testAutoVerifyPhone() {
        response.setAutoVerifyPhone(true);
        assertTrue(response.isAutoVerifyPhone());
    }
}
