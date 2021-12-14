package CreateAlgorithm;

import com.google.gson.Gson;

public class CreateAlgorithmResponse {
    public final String response;
    public final int httpCode;
    public final String error;

    public CreateAlgorithmResponse (String s, int code, String error) {
        this.response = s;
        this.httpCode = code;
        this.error = error;
    }

    // 200 means success
    public CreateAlgorithmResponse (String s, int code) {
        this.response = s;
        this.httpCode = code;
        this.error = "";
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
