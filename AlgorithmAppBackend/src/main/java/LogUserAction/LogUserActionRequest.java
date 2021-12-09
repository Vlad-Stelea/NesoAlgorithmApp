package LogUserAction;

import com.google.gson.Gson;

public class LogUserActionRequest {
    String username;
    String action;

    public LogUserActionRequest() {
        // Do Nothing
    }

    public LogUserActionRequest(String username, String action) {
        this.username = username;
        this.action = action;
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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
