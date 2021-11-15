package entities;

public class ProblemInstance {

    private String probInstanceUUID;
    private String probInstanceName;
    private String datasetURL;
    private String algoName;

    public ProblemInstance(String probInstanceUUID, String algoName) {
        this.probInstanceUUID = probInstanceUUID;
        this.algoName = algoName;
    }

    public ProblemInstance(String probInstanceUUID, String probInstanceName, String datasetURL, String algoName) {
        this.probInstanceUUID = probInstanceUUID;
        this.probInstanceName = probInstanceName;
        this.datasetURL = datasetURL;
        this.algoName = algoName;
    }

    public String getDatasetURL() { return datasetURL; }
    public void setDatasetURL(String datasetURL) { this.datasetURL = datasetURL; }

    public String getProbInstanceUUID() { return probInstanceUUID; }
    public void setProbInstanceUUID(String probInstanceUUID) { this.probInstanceUUID = probInstanceUUID; }

    public String getProbInstanceName() { return probInstanceName; }
    public void setProbInstanceName(String probInstanceName) { this.probInstanceName = probInstanceName; }

    public String getAlgoName() { return algoName; }
    public void setAlgoName(String algoName) { this.algoName = algoName; }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof ProblemInstance)) return false;
        ProblemInstance pi = (ProblemInstance) obj;
        return probInstanceUUID.equals(pi.getProbInstanceUUID()) && algoName.equals(pi.getAlgoName());
    }

}
