
package CreateImplementation;

import com.google.gson.Gson;

public class CreateImplementationRequest {

    public String implName;
    public String algoName;
    public String code;
    public String language;

    public CreateImplementationRequest(){
        // DO NOTHING
    }

    public CreateImplementationRequest(String implName, String codeUrl, String language, String algoName){
        this.implName = implName;
        this.algoName = algoName;
        this.code = codeUrl;
        this.language = language;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}