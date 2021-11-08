package CreateImplementation;

public class CreateImplemenationRequest {

    public String implName;
    public String algoName;
    public String codeUrl;
    public String language;


    public CreateImplemenationRequest(String implName, String algoName, String codeUrl, String language){
        this.implName = implName;
        this.algoName = algoName;
        this.codeUrl = codeUrl;
        this.language - language;
    }


    //getters and setters
    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
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

    @Override
    public String toString() {
        return "CreateImplemenation(" + implName + ", " + algoName + ", " + codeUrl +", " + language +")";
    }

}