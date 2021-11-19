package RemoveProblemInstance;

public class RemoveProblemInstanceRequest {

    String probInstanceUUID;
    
    public RemoveProblemInstanceRequest() {}

    public RemoveProblemInstanceRequest(String probInstanceUUID) {
        this.probInstanceUUID = probInstanceUUID;
    }

    public String getProbInstanceUUID() { return probInstanceUUID; }
    public void setProbInstanceUUID(String probInstanceUUID) { this.probInstanceUUID = probInstanceUUID; }

    @Override
    public String toString() {
        return "CreateProblemInstance(" + probInstanceUUID + ")";
    }
    
}
