package entities;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private String algoName;
    private String parentClassificationName;
    private List<Implementation> implementations;

    public Algorithm(String algoName) {
        this.algoName = algoName;
        parentClassificationName = null;
        implementations = new ArrayList<>();
    }

    public Algorithm(String algoName, String parentClassificationName) {
        this.algoName = algoName;
        this.parentClassificationName = parentClassificationName;
        implementations = new ArrayList<>();
    }

    public Algorithm(String algoName, String parentClassificationName, ArrayList<Implementation> implementations) {
        this.algoName = algoName;
        this.parentClassificationName = parentClassificationName;
        this.implementations = implementations;
    }

    //getters and setters
    public String getAlgoName() {
        return algoName;
    }

    public String getParentClassificationName() {
        return parentClassificationName;
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

    public void setParentClassificationName(String parentClassificationName) {
        this.parentClassificationName = parentClassificationName;
    }

    //other

    public boolean addImplementation(Implementation i) {
        i.setAlgorithmName(this.algoName);
        return implementations.add(i);
    }

    public boolean removeImplementation(String implName) {
        if(implementations.removeIf(i -> checkImplName(i, implName))){
            return true;
        }else{
            return false;
        }
    }

    private static boolean checkImplName(Implementation i, String name){
        if(i.getImplName().equals(name)){
            i.setAlgorithmName(null);
            return true;
        }
        return false;
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
        if(this.parentClassificationName != null){
            return this.parentClassificationName.equals(a.parentClassificationName);
        }else{
            return a.parentClassificationName == null;
        }
    }

    private boolean algoNamesMatch(Algorithm a) {
        if(this.algoName != null){
            return this.algoName.equals(a.algoName);
        }else{
            return a.algoName == null;
        }
    }

}
