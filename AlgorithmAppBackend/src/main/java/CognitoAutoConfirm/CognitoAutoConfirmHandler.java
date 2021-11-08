package CognitoAutoConfirm;

// Using handlers in this way allows us to inject dependencies in handlers which decouples code from aws stack and makes it more testable
// While this is not the best example of this since this lambda function does not have any dependencies, future ones will benefit from this
public class CognitoAutoConfirmHandler {
    public CognitoAutoConfirmHandler() {

    }

    public UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> handle(UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> request) {
        request.getResponse().autoConfirmUser = true;
        return request;
    }
}
