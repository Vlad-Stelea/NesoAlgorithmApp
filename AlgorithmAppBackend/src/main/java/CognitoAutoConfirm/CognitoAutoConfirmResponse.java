package CognitoAutoConfirm;

import com.google.gson.Gson;

public class CognitoAutoConfirmResponse {
    boolean autoConfirmUser;
    boolean autoVerifyEmail;
    boolean autoVerifyPhone;

    public CognitoAutoConfirmResponse() {
        // Do nothing
    }

    public boolean isAutoConfirmUser() {
        return autoConfirmUser;
    }

    public void setAutoConfirmUser(boolean autoConfirmUser) {
        this.autoConfirmUser = autoConfirmUser;
    }

    public boolean isAutoVerifyEmail() {
        return autoVerifyEmail;
    }

    public void setAutoVerifyEmail(boolean autoVerifyEmail) {
        this.autoVerifyEmail = autoVerifyEmail;
    }

    public boolean isAutoVerifyPhone() {
        return autoVerifyPhone;
    }

    public void setAutoVerifyPhone(boolean autoVerifyPhone) {
        this.autoVerifyPhone = autoVerifyPhone;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
