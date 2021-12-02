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

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getDatasetUrl() {
        return datasetUrl;
    }

    public void setDatasetUrl(String datasetUrl) {
        this.datasetUrl = datasetUrl;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    @Override
    public boolean equals(Object otherObject) {
        if(otherObject == null) return false;
        return this.toString().equals(otherObject.toString());
    }

}
