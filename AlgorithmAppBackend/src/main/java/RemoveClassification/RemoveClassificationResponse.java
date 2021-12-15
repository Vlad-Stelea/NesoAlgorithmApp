package RemoveClassification;

import com.google.gson.Gson;

import java.util.Objects;

public class RemoveClassificationResponse {

    String classificationName;
    int httpCode;
    String error;

    public RemoveClassificationResponse(String classificationName, int httpCode) {
        this.classificationName = classificationName;
        this.httpCode = httpCode;
    }

    public RemoveClassificationResponse(int httpCode, String error) {
        this.httpCode = httpCode;
        this.error = error;
    }

    public String getClassificationName() { return classificationName; }
    public void setClassificationName(String classificationName) { this.classificationName = classificationName; }

    public int getHttpCode() { return httpCode; }
    public void setHttpCode(int httpCode) { this.httpCode = httpCode; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveClassificationResponse that = (RemoveClassificationResponse) o;
        return Objects.equals(classificationName, that.classificationName) && Objects.equals(error, that.error);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
