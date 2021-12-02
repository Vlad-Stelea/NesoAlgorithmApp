package CreateProblemInstance;

import com.google.gson.Gson;

public class CreateProblemInstanceRequest {

    public String probInstanceName;
    public String datasetPayload;
    public String algoName;

    public CreateProblemInstanceRequest() {

    }

    public CreateProblemInstanceRequest(String probInstanceName, String datasetPayload, String algoName) {
        this.probInstanceName = probInstanceName;
        this.datasetPayload = datasetPayload;
        this.algoName = algoName;
    }

    public String getProbInstanceName() {
        return probInstanceName;
    }

    public void setProbInstanceName(String probInstanceName) {
        this.probInstanceName = probInstanceName;
    }

    public String getDatasetPayload() {
        return datasetPayload;
    }

    public void setDatasetPayload(String datasetPayload) {
        this.datasetPayload = datasetPayload;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
