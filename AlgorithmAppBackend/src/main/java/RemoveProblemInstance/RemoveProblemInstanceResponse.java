package RemoveProblemInstance;

import com.google.gson.Gson;

public class RemoveProblemInstanceResponse {

    public final String problemInstanceID;
    public final int httpCode;
    public final String error;

    public RemoveProblemInstanceResponse(String s, int code) {
        this.problemInstanceID = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public RemoveProblemInstanceResponse(String s, int code, String error) {
        this.problemInstanceID = s;
        this.httpCode = code;
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
