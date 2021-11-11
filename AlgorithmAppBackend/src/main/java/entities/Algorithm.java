package entities;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private String algoName;
    private Classification parentClassification;
    private List<Implementation> implementations;

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

    public List<Implementation> getImplementations() {
        return implementations;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public void setImplementations(List<Implementation> implementations) {
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
        return this.algoNamesMatch(a) && this.parentClassificationsMatch(a) && this.implementationsMatch(a);
    }

    private boolean implementationsMatch(Algorithm a) {
        if(this.implementations != null){
            if(a.implementations != null && this.implementations.size() == a.implementations.size()){
                for(int i = 0; i < a.implementations.size(); i++){
                    if(!this.implementations.contains(a.implementations.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either c has null implentations or the sizes don't match
                return false;
            }
        }else{
            return a.implementations == null;
        }
    }

    private boolean parentClassificationsMatch(Algorithm a) {
        //todo when we set parent to be a string implement this, if we implement now it will cause an infinite loop

        return true;
    }

    private boolean algoNamesMatch(Algorithm a) {
        if(this.algoName != null){
            return this.algoName.equals(a.algoName);
        }else{
            return a.algoName == null;
        }
    }

}
