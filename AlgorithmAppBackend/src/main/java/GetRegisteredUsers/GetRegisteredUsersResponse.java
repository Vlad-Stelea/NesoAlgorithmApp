package GetRegisteredUsers;

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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
