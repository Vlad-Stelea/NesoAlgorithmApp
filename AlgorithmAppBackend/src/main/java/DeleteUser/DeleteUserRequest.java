package DeleteUser;

import com.google.gson.Gson;

public class DeleteUserRequest {
    String username;

    public DeleteUserRequest() {
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

    @Override
    public boolean equals(Object otherObject) {
        return this.toString().equals(otherObject.toString());
    }
}
