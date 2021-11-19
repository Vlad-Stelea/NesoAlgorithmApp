package ReclassifyAlgorithm;

public class ReclassifyAlgorithmRequest {

    private String algoName;
    private String newClassName;

    public ReclassifyAlgorithmRequest() {}
    public ReclassifyAlgorithmRequest(String algoName, String newClassName) {
        this.algoName = algoName;
        this.newClassName = newClassName;
    }

    public String getAlgoName() { return algoName; }
    public void setAlgoName(String algoName) { this.algoName = algoName; }

    public String getNewClassName() { return newClassName; }
    public void setNewClassName(String newClassName) { this.newClassName = newClassName; }

    @Override
    public String toString() {
        return "ReclassifyAlgorithm(" + algoName + "," + newClassName + ")";
    }

}
