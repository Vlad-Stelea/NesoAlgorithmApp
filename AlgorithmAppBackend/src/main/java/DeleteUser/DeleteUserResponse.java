package DeleteUser;

import com.google.gson.Gson;

public class DeleteUserResponse {
    int statusCode;

    String username;

    String error;

    public DeleteUserResponse(int statusCode, String username) {
        this.statusCode = statusCode;
        this.username = username;
    }

    // Note can't set error string because constructors would have same signature
    public DeleteUserResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object otherObject) {
        return this.toString().equals(otherObject.toString());
    }
}
