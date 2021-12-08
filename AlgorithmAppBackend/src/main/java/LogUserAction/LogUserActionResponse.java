package LogUserAction;

import entities.PrettyDate;

public class LogUserActionResponse {
    int statusCode;

    String activityLogUUID;
    String username;
    String action;
    PrettyDate date;

    String error;

    public LogUserActionResponse() {
        // Do Nothing
    }

    public LogUserActionResponse(int statusCode, String activityLogUUID, String username, String action, PrettyDate date) {
        this.statusCode = statusCode;
        this.activityLogUUID = activityLogUUID;
        this.username = username;
        this.action = action;
        this.date = date;
    }

    public LogUserActionResponse(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getActivityLogUUID() {
        return activityLogUUID;
    }

    public void setActivityLogUUID(String activityLogUUID) {
        this.activityLogUUID = activityLogUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public PrettyDate getDate() {
        return date;
    }

    public void setDate(PrettyDate date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
