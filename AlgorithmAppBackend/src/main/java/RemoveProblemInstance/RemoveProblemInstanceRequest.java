package RemoveProblemInstance;

public class RemoveProblemInstanceRequest {

    String problemInstanceID;
    
    public RemoveProblemInstanceRequest() {}

    public RemoveProblemInstanceRequest(String problemInstanceID) {
        this.problemInstanceID = problemInstanceID;
    }

    public String getProblemInstanceID() { return problemInstanceID; }
    public void setProblemInstanceID(String problemInstanceID) { this.problemInstanceID = problemInstanceID; }

    @Override
    public String toString() {
        return "CreateProblemInstance(" + problemInstanceID + ")";
    }
    
}
