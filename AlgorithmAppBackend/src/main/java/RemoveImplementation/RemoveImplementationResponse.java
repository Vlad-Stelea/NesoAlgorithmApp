package RemoveImplementation;

import com.google.gson.Gson;

public class RemoveImplementationResponse {

    final String implementationID;
    final int httpCode;
    final String error;

    public RemoveImplementationResponse(String s, int code) {
        this.implementationID = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public RemoveImplementationResponse(String s, int code, String error) {
        this.implementationID = s;
        this.httpCode = code;
        this.error = error;
    }

    public String getImplementationID() {
        return implementationID;
    }

    public int getHttpCode() {
        return httpCode;
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
