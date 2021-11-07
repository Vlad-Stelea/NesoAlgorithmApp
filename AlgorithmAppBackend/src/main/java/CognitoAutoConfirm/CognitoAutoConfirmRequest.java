package CognitoAutoConfirm;

import com.google.gson.Gson;

import java.util.Map;

public class CognitoAutoConfirmRequest {
    Map<String, String> userAttributes;

    public CognitoAutoConfirmRequest() {
        // Do nothing
    }

    public Map<String, String> getUserAttributes() {
        return this.userAttributes;
    }

    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
