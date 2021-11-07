package CreateAlgorithm;

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

    public String toString() {
        return "CreateAlgorithmResponse(" + response + ")";
    }
}
