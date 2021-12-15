package RemoveImplementation;

import com.google.gson.Gson;

public class RemoveImplementationRequest {

    String implName;
    String algoName;

    public RemoveImplementationRequest() {}

    public RemoveImplementationRequest(String implName, String algoName) {
        this.implName = implName;
        this.algoName = algoName;
    }

    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
    }

    public String getAlgoName() { return algoName; }

    public void setAlgoName(String algoName) { this.algoName = algoName; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
