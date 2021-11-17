package CreateImplementation;
import com.google.gson.Gson;

import static EqualityUtils.EqualityUtils.equalOrBothNull;


public class CreateImplementationResponse {

    int statusCode;

    String implName;
    String algoName;
    String codeUrl;
    String language;
    String error;

    public CreateImplementationResponse(int statusCode, String implName, String algoName, String codeUrl, String language) {
        this.statusCode = statusCode;
        this.implName = implName;
        this.algoName = algoName;
        this.codeUrl = codeUrl;
        this.language = language;
    }

    public CreateImplementationResponse(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if(o instanceof CreateImplementationResponse) {
            CreateImplementationResponse otherResponse = (CreateImplementationResponse) o;
            return otherResponse.statusCode == this.statusCode &&
                    equalOrBothNull(otherResponse.implName, this.implName) &&
                    equalOrBothNull(otherResponse.algoName, this.algoName) &&
                    equalOrBothNull(otherResponse.codeUrl, this.codeUrl) &&
                    equalOrBothNull(otherResponse.language, this.language) &&
                    equalOrBothNull(otherResponse.error, this.error);
        } else {
            return false;
        }
    }
}
