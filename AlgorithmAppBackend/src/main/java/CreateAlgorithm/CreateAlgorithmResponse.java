package CreateAlgorithm;

public class CreateAlgorithmResponse {
    public String result;
    public int statusCode;
    public String error;

    public CreateAlgorithmResponse (double value, int statusCode) {
        this.result = "" + value; // doesn't matter since error
        this.statusCode = statusCode;
        this.error = "";
    }

    public CreateAlgorithmResponse (int statusCode, String errorMessage) {
        this.result = ""; // doesn't matter since error
        this.statusCode = statusCode;
        this.error = errorMessage;
    }

    public String toString() {
        if (statusCode / 100 == 2) {  // too cute?
            return "Result(" + result + ")";
        } else {
            return "ErrorResult(" + statusCode + ", err=" + error + ")";
        }
    }
}
