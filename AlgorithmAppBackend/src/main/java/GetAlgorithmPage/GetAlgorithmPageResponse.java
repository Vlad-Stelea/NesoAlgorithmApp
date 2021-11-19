package GetAlgorithmPage;

import com.google.gson.Gson;
import entities.Algorithm;
import entities.AlgorithmPage;


public class GetAlgorithmPageResponse {

    public AlgorithmPage algorithmPage;
    public final int statusCode;
    public final String error;

    public GetAlgorithmPageResponse(AlgorithmPage algorithmPage, int statusCode) {
        this.algorithmPage = algorithmPage;
        this.statusCode = statusCode;
        this.error = "";
    }

    public GetAlgorithmPageResponse(int code, String errorMessage) {
        this.algorithmPage = null;
        this.statusCode = code;
        this.error = errorMessage;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
