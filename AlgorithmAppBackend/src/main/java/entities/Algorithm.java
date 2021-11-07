package entities;

import java.util.ArrayList;

public class Algorithm {

    private String algoName;
    private Classification parentClassification;
    private ArrayList<Implementation> implementations;

    public Algorithm(String algoName) {
        this.algoName = algoName;
        parentClassification = null;
        implementations = new ArrayList<>();
    }

    public Algorithm(String algoName, Classification parentClassification) {
        this.algoName = algoName;
        this.parentClassification = parentClassification;
        implementations = new ArrayList<>();
    }

    public Algorithm(String algoName, Classification parentClassification, ArrayList<Implementation> implementations) {
        this.algoName = algoName;
        this.parentClassification = parentClassification;
        this.implementations = implementations;
    }

    //getters and setters

    public String getAlgoName() {
        return algoName;
    }

    public Classification getParentClassification() {
        return parentClassification;
    }

    public ArrayList<Implementation> getImplementations() {
        return implementations;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public void setImplementations(ArrayList<Implementation> implementations) {
        this.implementations = implementations;
    }

    public void setParentClassification(Classification parentClassification) {
        this.parentClassification = parentClassification;
    }

    //other

    public boolean addImplementation(Implementation i) {
        return implementations.add(i);
    }

    public boolean removeImplementation(String implName) {
        return implementations.removeIf(a -> a.getImplName().equals(implName));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Algorithm)) return false;
        Algorithm a = (Algorithm) obj;
        return a.getAlgoName().equals(this.algoName);
    }

}
