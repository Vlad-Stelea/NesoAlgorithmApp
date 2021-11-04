package http;

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

    public String toString() {
        if (httpCode == 200) {
            return "CreateClassificationResponse(" + response + ")";
        } else {
            return "ErrorResult(httpCode = " + httpCode + ", error = " + error + ")";
        }
    }

}
