package RemoveAlgorithm;

import com.google.gson.Gson;

public class RemoveAlgorithmResponse {

    final String algoName;
    final int httpCode;
    final String error;

    public RemoveAlgorithmResponse(String s, int code) {
        this.algoName = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public RemoveAlgorithmResponse(String s, int code, String error) {
        this.algoName = s;
        this.httpCode = code;
        this.error = error;
    }

    public String getAlgoName() {
        return algoName;
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
