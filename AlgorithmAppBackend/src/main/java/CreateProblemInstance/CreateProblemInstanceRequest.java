package CreateProblemInstance;

public class CreateProblemInstanceRequest {

    public String probInstanceUUID;
    public String probInstanceName;
    public String encodedDatasetContents;
    public String algoName;

    public CreateProblemInstanceRequest() {}

    public CreateProblemInstanceRequest(String probInstanceUUID, String probInstanceName, String encodedDatasetContents, String algoName) {
        this.probInstanceUUID = probInstanceUUID;
        this.probInstanceName = probInstanceName;
        this.encodedDatasetContents = encodedDatasetContents;
        this.algoName = algoName;
    }

    public String getProbInstanceUUID() { return probInstanceUUID; }
    public void setProbInstanceUUID(String probInstanceUUID) { this.probInstanceUUID = probInstanceUUID; }

    public String getAlgoName() { return algoName; }
    public void setAlgoName(String algoName) { this.algoName = algoName; }

    public String getProbInstanceName() { return probInstanceName; }
    public void setProbInstanceName(String probInstanceName) { this.probInstanceName = probInstanceName; }

    public String getEncodedDatasetContents() { return encodedDatasetContents; }
    public void setEncodedDatasetContents(String datasetURL) { this.encodedDatasetContents = datasetURL; }

    @Override
    public String toString() {
        return "CreateProblemInstance(" + probInstanceUUID + "," + probInstanceName + "," + encodedDatasetContents +"," + algoName + ")";
    }
}
