package RemoveAlgorithm;

import com.google.gson.Gson;

public class RemoveAlgorithmRequest {

    public String algoName;

    public RemoveAlgorithmRequest(){}

    public RemoveAlgorithmRequest(String algoName){
        this.algoName = algoName;
    }

    public void setAlgoName(String algoName){
        this.algoName = algoName;
    }

    public String getAlgoName(){
        return this.algoName;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
