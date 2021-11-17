package CreateBenchmark;


import com.google.gson.Gson;

public class CreateBenchmarkResponse {
    public final String response;
    public final int httpCode;
    public final String error;


    public CreateBenchmarkResponse (String s, int code) {
        this.response = s;
        this.httpCode = code;
        this.error = "";
    }

    // 200 means success
    public CreateBenchmarkResponse (String s, int code, String error) {
        this.response = s;
        this.httpCode = code;
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
