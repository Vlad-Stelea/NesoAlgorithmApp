package GetHierarchy;

import com.google.gson.Gson;
import entities.*;

import java.util.ArrayList;
import java.util.List;

public class GetHierarchyResponse {

    // TODO will need to create lists of all of the things we need in our full hierarchy display
    public List<Classification> topClassifications;
    public final int statusCode;
    public final String error;

    public GetHierarchyResponse(List<Classification> classifications, int statusCode) {
        this.topClassifications = classifications;
        this.statusCode = statusCode;
        this.error = "";
    }

    public GetHierarchyResponse(int code, String errorMessage) {
        this.topClassifications = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    public List<Classification> getTopClassifications() {
        return topClassifications;
    }

    public void setTopClassifications(List<Classification> topClassifications) {
        this.topClassifications = topClassifications;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
