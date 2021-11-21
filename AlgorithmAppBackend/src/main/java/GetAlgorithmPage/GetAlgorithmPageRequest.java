package GetAlgorithmPage;

public class GetAlgorithmPageRequest {

    String algoName;

    public GetAlgorithmPageRequest(){}

    public GetAlgorithmPageRequest(String algoName){
        this.algoName = algoName;

    }

    public String getAlgoName(){
        return algoName;
    }

    public void setAlgoName(String algoName){
        this.algoName = algoName;
    }



    @Override
    public String toString() {
        return "GetAlgorithmPage(" + algoName + ")";
    }

}
