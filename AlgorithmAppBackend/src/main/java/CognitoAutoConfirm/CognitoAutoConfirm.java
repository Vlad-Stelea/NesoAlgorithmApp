package CognitoAutoConfirm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CognitoAutoConfirm implements RequestHandler
        <UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse>,
        UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse>> {


    @Override
    public UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> handleRequest(UserPoolEvent<CognitoAutoConfirmRequest, CognitoAutoConfirmResponse> input, Context context) {
        return new CognitoAutoConfirmHandler().handle(input);
    }
}
