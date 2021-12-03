package RemoveBenchmark;

import com.google.gson.Gson;

public class RemoveBenchmarkRequest {

    String benchmarkID;

    public RemoveBenchmarkRequest() {}

    public RemoveBenchmarkRequest(String benchmarkID) {
        this.benchmarkID = benchmarkID;
    }

    public String getBenchmarkID() {
        return benchmarkID;
    }

    public void setBenchmarkID(String benchmarkID) {
        this.benchmarkID = benchmarkID;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
