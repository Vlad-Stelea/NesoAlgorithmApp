package CreateImplementation;

public class CreateImplemenationRequest {

    public String implName;
    public String algoName;


    public CreateImplemenationRequest(String implName, String algoName){
        this.implName = implName;
        this.algoName = algoName;
    }



    //getters and setters
    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
    }

    @Override
    public String toString() {
        return "CreateImplemenation(" + implName + ", " + algoName + ")";
    }

}