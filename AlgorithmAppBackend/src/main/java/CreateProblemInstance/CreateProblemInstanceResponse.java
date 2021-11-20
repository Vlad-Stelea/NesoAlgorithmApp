package CreateProblemInstance;

import com.google.gson.Gson;

public class CreateProblemInstanceResponse {

    String instanceId;
    String instanceName;
    String datasetUrl;
    String algorithmName;
    int httpCode;
    String error;

    public CreateProblemInstanceResponse(String instanceId, String instanceName, String datasetUrl, String algorithmName, int httpCode) {
        this.instanceId = instanceId;
        this.instanceName = instanceName;
        this.datasetUrl = datasetUrl;
        this.algorithmName = algorithmName;
        this.httpCode = httpCode;
    }

    public CreateProblemInstanceResponse(int httpCode, String error) {
        this.httpCode = httpCode;
        this.error = error;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String getDatasetUrl() {
        return datasetUrl;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getError() {
        return error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
