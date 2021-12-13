package GetUserActions;

import com.amazonaws.services.mq.model.User;
import com.google.gson.Gson;
import entities.UserAction;
import java.util.ArrayList;
import java.util.List;
public class GetUserActionsResponse {
    public List<UserAction> userActionList;
    public final int statusCode;
    public final String error;


    public GetUserActionsResponse(List<UserAction> userActionList, int statusCode) {
        this.userActionList = userActionList;
        this.statusCode = statusCode;
        this.error = "";
    }

    public GetUserActionsResponse(int code, String errorMessage) {
        this.userActionList = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    public List<UserAction> getUserActionList() {
        return userActionList;
    }

    public void setUserActionList(List<UserAction> userActionList) {
        this.userActionList = userActionList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
