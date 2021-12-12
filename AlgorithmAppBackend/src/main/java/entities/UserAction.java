package entities;

import java.util.Date;

public class UserAction {
   String activityLogUUID;
   String username;
   String action;
   Date date;

    public UserAction(String activityLogUUID, String username, String action, Date date) {
        this.activityLogUUID = activityLogUUID;
        this.username = username;
        this.action = action;
        this.date = date;
    }

    public String getActivityLogUUID() {
        return activityLogUUID;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public Date getDate() {
        return date;
    }
}
