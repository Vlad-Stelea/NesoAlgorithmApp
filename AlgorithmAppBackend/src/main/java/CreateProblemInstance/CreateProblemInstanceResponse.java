package CreateProblemInstance;

import com.google.gson.Gson;
import static Utils.EqualityUtils.equalOrBothNull;
public class CreateProblemInstanceResponse {

    int statusCode;
    String error;

    String probInstanceUUID;
    String probInstanceName;
    String encodedDatasetContents;
    String algoName;

    public CreateProblemInstanceResponse(int statusCode, String probInstanceUUID, String probInstanceName, String encodedDatasetContents, String algoName) {
        this.statusCode = statusCode;
        this.probInstanceUUID = probInstanceUUID;
        this.probInstanceName = probInstanceName;
        this.encodedDatasetContents = encodedDatasetContents;
        this.algoName = algoName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProbInstanceUUID() {
        return probInstanceUUID;
    }

    public void setProbInstanceUUID(String probInstanceUUID) {
        this.probInstanceUUID = probInstanceUUID;
    }

    public String getProbInstanceName() {
        return probInstanceName;
    }

    public void setProbInstanceName(String probInstanceName) {
        this.probInstanceName = probInstanceName;
    }

    public String getEncodedDatasetContents() {
        return encodedDatasetContents;
    }

    public void setEncodedDatasetContents(String encodedDatasetContents) {
        this.encodedDatasetContents = encodedDatasetContents;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    // 200 means success
    public CreateProblemInstanceResponse(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if(o instanceof CreateProblemInstanceResponse) {
            CreateProblemInstanceResponse otherResponse = (CreateProblemInstanceResponse) o;
            return otherResponse.statusCode == this.statusCode &&
                    equalOrBothNull(otherResponse.probInstanceUUID, this.probInstanceUUID) &&
                    equalOrBothNull(otherResponse.probInstanceName, this.probInstanceName) &&
                    equalOrBothNull(otherResponse.encodedDatasetContents, this.encodedDatasetContents) &&
                    equalOrBothNull(otherResponse.algoName, this.algoName) &&
                    equalOrBothNull(otherResponse.error, this.error);
        } else {
            return false;
        }
    }

}
