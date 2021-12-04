package RemoveBenchmark;

import com.google.gson.Gson;

public class RemoveBenchmarkResponse {

    final String benchmarkID;
    final int httpCode;
    final String error;

    public RemoveBenchmarkResponse(String s, int code) {
        this.benchmarkID = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public RemoveBenchmarkResponse(String s, int code, String error) {
        this.benchmarkID = s;
        this.httpCode = code;
        this.error = error;
    }

    public String getBenchmarkID() {
        return benchmarkID;
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
