package CognitoAutoConfirm;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CognitoAutoConfirmTest {
    @Test
    public void testHandle() {
        UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> event = new UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse>(
                new CognitoAutoConfirmRequest(),
                new CognitoAutoConfirmResponse()
        );

        assertFalse(event.response.autoConfirmUser);
        UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> returnedEvent = new CognitoAutoConfirmHandler().handle(event);
        assertTrue(returnedEvent.response.autoConfirmUser);
    }
}
