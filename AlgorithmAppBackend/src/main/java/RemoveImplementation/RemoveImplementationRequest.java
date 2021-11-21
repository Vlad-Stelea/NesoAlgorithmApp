package RemoveImplementation;

import com.google.gson.Gson;

public class RemoveImplementationRequest {

    String implementationID;

    public RemoveImplementationRequest() {}

    public RemoveImplementationRequest(String implementationID) {
        this.implementationID = implementationID;
    }

    public String getImplementationID() {
        return implementationID;
    }

    public void setImplementationID(String implementationID) {
        this.implementationID = implementationID;
    }

    public String getImplName() {
        return implementationID.split(",")[0];
    }

    public String getAlgoName() {
        return implementationID.split(",")[1];
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
