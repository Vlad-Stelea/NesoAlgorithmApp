package RemoveImplementation;

import com.google.gson.Gson;

public class RemoveImplementationResponse {

    String implName;
    String algoName;
    final int httpCode;
    final String error;

    public RemoveImplementationResponse(String implName, String algoName, int code) {
        this.implName = implName;
        this.algoName = algoName;
        this.httpCode = code;
        this.error = "";
    }

    public RemoveImplementationResponse(int code, String error) {
        this.httpCode = code;
        this.error = error;
    }

    public String getImplName() {
        return implName;
    }

    public String getAlgoName() { return algoName; }

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
