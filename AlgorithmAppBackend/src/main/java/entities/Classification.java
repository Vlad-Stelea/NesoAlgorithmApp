package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Classification {

    private final String className;
    private Classification parentClassification;
    private List<Algorithm> algorithms;
    private List<Classification> subclassifications;

    public Classification(String className) {
        this.className = className;
        parentClassification = null;
        algorithms = new ArrayList<>();
        subclassifications = new ArrayList<>();
    }

    public Classification(String className, Classification parentClassification) {
        this.className = className;
        this.parentClassification = parentClassification;
        algorithms = new ArrayList<>();
        subclassifications = new ArrayList<>();
    }

    public Classification(String className, Classification parentClassification, ArrayList<Algorithm> algorithms, ArrayList<Classification> subclassifications) {
        this.className = className;
        this.parentClassification = parentClassification;
        this.algorithms = algorithms;
        this.subclassifications = subclassifications;
    }

    // getter and setter methods
    public String getClassName() {
        return className;
    }

    public Classification getParentClassification() {
        return parentClassification;
    }

    public void setParentClassification(Classification parentClassification) {
        this.parentClassification = parentClassification;
    }

    public List<Algorithm> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public List<Classification> getSubclassifications() {
        return subclassifications;
    }

    public void setSubclassifications(List<Classification> subclassifications) {
        this.subclassifications = subclassifications;
    }


  
    // non-getter and setter methods
    public boolean addAlgorithm(Algorithm a) {
        return algorithms.add(a);
    }

    public boolean removeAlgorithm(String algoName) {
        return algorithms.removeIf(a -> a.getAlgoName().equals(algoName));
    }

    public boolean addSubclassification(Classification c) {
        return subclassifications.add(c);
    }

    public boolean removeSubclassification(String className) {
        for(Classification c : subclassifications) {
            if(c.getClassName().equals(className)) {
                c.setParentClassification(null);
                return subclassifications.remove(c);
            }
        }

        return false;
    }

    public boolean isTopLevel() {
        return parentClassification == null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Classification)) return false;
        Classification c = (Classification) obj;
        return c.getClassName().equals(this.className);
    }
}
