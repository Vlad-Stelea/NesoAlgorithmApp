package GetAlgorithmPage;

import com.google.gson.Gson;
import entities.Algorithm;


public class GetAlgorithmPageResponse {

    public Algorithm algorithm;
    public final int statusCode;
    public final String error;

    public GetAlgorithmPageResponse(Algorithm algorithm, int statusCode) {
        this.algorithm = algorithm;
        this.statusCode = statusCode;
        this.error = "";
    }

    public GetAlgorithmPageResponse(int code, String errorMessage) {
        this.algorithm = null;
        this.statusCode = code;
        this.error = errorMessage;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
