package GetRegisteredUsers;

import com.google.gson.Gson;

import java.util.List;

public class GetRegisteredUsersResponse {

    int statusCode;
    List<String> users;

    String error;

    public GetRegisteredUsersResponse() {
    }

    public GetRegisteredUsersResponse(int statusCode, List<String> users) {
        this.statusCode = statusCode;
        this.users = users;
    }

    public GetRegisteredUsersResponse(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
