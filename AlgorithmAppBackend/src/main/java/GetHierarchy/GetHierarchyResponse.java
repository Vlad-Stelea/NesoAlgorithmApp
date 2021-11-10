package GetHierarchy;

import com.google.gson.Gson;
import entities.*;

import java.util.ArrayList;
import java.util.List;

public class GetHierarchyResponse {

    // TODO will need to create lists of all of the things we need in our full hierarchy display
    public List<Classification> classifications;
    public List<Algorithm> algorithms;
    public List<Implementation> implementations;
    public final int statusCode;
    public final String error;

    public GetHierarchyResponse(List<Classification> classifications, List<Algorithm> algorithms, List<Implementation> implementations, int statusCode) {
        this.classifications = classifications;
        this.algorithms = algorithms;
        this.implementations = implementations;
        this.statusCode = statusCode;
        this.error = "";
    }

    // TODO  get rid of this constructor and its usage
    public GetHierarchyResponse(List<Classification> classifications, List<Algorithm> algorithms, int statusCode) {
        this.classifications = classifications;
        this.algorithms = algorithms;
        this.implementations = new ArrayList<>();
        this.statusCode = statusCode;
        this.error = "";
    }

    public GetHierarchyResponse(int code, String errorMessage) {
        this.classifications = new ArrayList<>();
        this.algorithms = new ArrayList<>();
        this.implementations = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
