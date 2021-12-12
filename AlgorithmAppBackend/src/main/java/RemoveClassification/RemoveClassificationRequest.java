package RemoveClassification;

import com.google.gson.Gson;

public class RemoveClassificationRequest {

    String classificationName;

    public RemoveClassificationRequest() {}

    public RemoveClassificationRequest(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getClassificationName() { return classificationName; }
    public void setClassificationName(String classificationName) { this.classificationName = classificationName; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
