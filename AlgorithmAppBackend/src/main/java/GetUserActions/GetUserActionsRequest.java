package GetUserActions;

import com.google.gson.Gson;

public class GetUserActionsRequest {
    public String username;

    public GetUserActionsRequest(){}
    public GetUserActionsRequest(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
