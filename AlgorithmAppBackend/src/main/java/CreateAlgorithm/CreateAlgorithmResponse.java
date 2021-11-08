package CreateAlgorithm;

import com.google.gson.Gson;

public class CreateAlgorithmResponse {
    public final String response;
    public final int httpCode;

    public CreateAlgorithmResponse (String s, int code) {
        this.response = s;
        this.httpCode = code;
    }

    // 200 means success
    public CreateAlgorithmResponse (String s) {
        this.response = s;
        this.httpCode = 200;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
