package CreateAlgorithm;

public class CreateAlgorithmRequest {

    String algoName;
    String className;

    public CreateAlgorithmRequest(){}

    public CreateAlgorithmRequest(String algoName, String className){
        this.algoName = algoName;
        this.className = className;

    }

    public String getAlgoName(){
        return algoName;
    }

    public void setAlgoName(String algoName){
        this.algoName = algoName;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }


    @Override
    public String toString() {
        return "CreateAlgorithm(" + algoName + ", " + className + ")";
    }

}
