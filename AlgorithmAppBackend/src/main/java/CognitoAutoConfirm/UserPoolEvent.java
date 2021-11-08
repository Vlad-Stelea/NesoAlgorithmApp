package CognitoAutoConfirm;

import com.google.gson.Gson;

public class UserPoolEvent<Req, Res> {
    String version;
    String region;
    String userPoolId;
    String userName;
    CallerContext callerContext;
    String triggerSource;


    Req request;
    Res response;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTriggerSource() {
        return triggerSource;
    }

    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserPoolEvent() {
        // Do Nothing
    }

    public UserPoolEvent(Req request, Res response) {
        this.request = request;
        this.response = response;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public CallerContext getCallerContext() {
        return callerContext;
    }

    public void setCallerContext(CallerContext callerContext) {
        this.callerContext = callerContext;
    }

    public Req getRequest() {
        return this.request;
    }

    public void setRequest(Req request) {
        this.request = request;
    }

    public Res getResponse() {
        return response;
    }

    public void setResponse(Res response) {
        this.response = response;
    }

    // Returns a jsonified version of this class
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static class CallerContext {
        String awsSdkVersion;
        String clientId;

        public CallerContext() {
            awsSdkVersion = "";
            clientId = "";
        }

        public String getAwsSdkVersion() {
            return awsSdkVersion;
        }

        public void setAwsSdkVersion(String awsSdkVersion) {
            this.awsSdkVersion = awsSdkVersion;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        @Override
        public boolean equals(Object otherObj) {
            if(otherObj == this) return true;
            if(!(otherObj instanceof CallerContext)) return false;
            CallerContext castOther = (CallerContext) otherObj;
            return (castOther.awsSdkVersion.equals(this.awsSdkVersion))
                    && (castOther.clientId.equals(this.clientId));
         }
    }
}
