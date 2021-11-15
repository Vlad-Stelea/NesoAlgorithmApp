package CreateProblemInstance;

import com.google.gson.Gson;

public class CreateProblemInstanceResponse {

    public final String response;
    public final int httpCode;
    public final String error;

    public CreateProblemInstanceResponse(String s, int code) {
        this.response = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public CreateProblemInstanceResponse(String s, int code, String error) {
        this.response = s;
        this.httpCode = code;
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
