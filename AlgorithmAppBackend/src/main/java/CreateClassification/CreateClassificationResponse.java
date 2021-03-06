package CreateClassification;

import com.google.gson.Gson;

public class CreateClassificationResponse {

    public final String response;
    public final int httpCode;
    public final String error;

    public CreateClassificationResponse(String s, int code) {
        this.response = s;
        this.httpCode = code;
        this.error = "";
    }

    public CreateClassificationResponse(String s, int code, String error) {
        this.response = s;
        this.httpCode = code;
        this.error = error;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
