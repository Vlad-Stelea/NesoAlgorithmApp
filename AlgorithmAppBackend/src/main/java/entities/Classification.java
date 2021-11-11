package entities;

import java.util.ArrayList;
import java.util.List;

public class Classification {

    private final String className;
    private String parentClassificationName;
    private List<Algorithm> algorithms;
    private List<Classification> subclassifications;

    public Classification(String className) {
        this.className = className;
        parentClassificationName = null;
        algorithms = new ArrayList<>();
        subclassifications = new ArrayList<>();
    }

    public Classification(String className, String parentClassificationName) {
        this.className = className;
        this.parentClassificationName = parentClassificationName;
        algorithms = new ArrayList<>();
        subclassifications = new ArrayList<>();
    }

    public Classification(String className, String parentClassificationName, ArrayList<Algorithm> algorithms, ArrayList<Classification> subclassifications) {
        this.className = className;
        this.parentClassificationName = parentClassificationName;
        this.algorithms = algorithms;
        this.subclassifications = subclassifications;
    }

    // getter and setter methods
    public String getClassName() {
        return className;
    }

    public String getParentClassificationName() {
        return parentClassificationName;
    }

    public void setParentClassificationName(String parentClassificationName) {
        this.parentClassificationName = parentClassificationName;
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
        a.setParentClassificationName(this.className);
        return algorithms.add(a);
    }

    public boolean removeAlgorithm(String algoName) {
        if(algorithms.removeIf(a -> checkAlgoName(a, algoName))){
            return true;
        }else{
            return false;
        }
    }

    private static boolean checkAlgoName(Algorithm i, String name){
        if(i.getAlgoName().equals(name)){
            i.setParentClassificationName(null);
            return true;
        }
        return false;
    }

    public boolean addSubclassification(Classification c) {
        c.setParentClassificationName(this.className);
        return subclassifications.add(c);
    }

    public boolean removeSubclassification(String className) {
        for(Classification c : subclassifications) {
            if(c.getClassName().equals(className)) {
                c.setParentClassificationName(null);
                return subclassifications.remove(c);
            }
        }

        return false;
    }

    public boolean isTopLevel() {
        return parentClassificationName == null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Classification)) return false;
        Classification c = (Classification) obj;
        //System.out.println("Classesequals");
        if(!(this.classNamesMatch(c) && this.subClassificationsMatch(c) && this.algorithmsMatch(c) && this.parentClassificationsMatch(c))){
            System.out.println("Classes");
        }
        return this.classNamesMatch(c) && this.subClassificationsMatch(c) && this.algorithmsMatch(c) && this.parentClassificationsMatch(c);
    }

    private boolean parentClassificationsMatch(Classification c) {
        if(this.parentClassificationName != null){
            return this.parentClassificationName.equals(c.parentClassificationName);
        }else{
            return c.parentClassificationName == null;
        }
    }

    private boolean algorithmsMatch(Classification c) {
        if(this.algorithms != null){
            if(c.algorithms != null && this.algorithms.size() == c.algorithms.size()){
                for(int i = 0; i < c.algorithms.size(); i++){
                    if(!this.algorithms.contains(c.algorithms.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either c has null algorithms or the sizes don't match
                return false;
            }
        }else{
            return c.algorithms == null;
        }
    }

    private boolean subClassificationsMatch(Classification c) {
        if(this.subclassifications != null){
            if(c.subclassifications != null && this.subclassifications.size() == c.subclassifications.size()){
                for(int i = 0; i < c.subclassifications.size(); i++){
                    if(!this.subclassifications.contains(c.subclassifications.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either c has null subClassifications or the sizes don't match
                return false;
            }
        }else{
            return c.subclassifications == null;
        }
    }

    private boolean classNamesMatch(Classification c) {
        if(this.className != null){
            return this.className.equals(c.className);
        }else{
            return c.className == null;
        }
    }
}
