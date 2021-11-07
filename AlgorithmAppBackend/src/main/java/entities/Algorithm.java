package entities;

public class Algorithm {

    private String algoName;

    public Algorithm() {

    }

    public Algorithm(String algoName) {
        this.algoName = algoName;
    }

    public String getAlgoName() {
        return algoName;
    }

    public boolean setAlgoName(String algoName) {
        this.algoName = algoName;
        return true;
    }

}
