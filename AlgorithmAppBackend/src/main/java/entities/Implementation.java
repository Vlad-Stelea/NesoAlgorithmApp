package entities;

import java.util.ArrayList;
import java.util.List;

public class Implementation {

    private String implName;
    private String codeURL;
    private String language;
    private String algorithmName;
    private List<Benchmark> benchmarks;

    public Implementation(String implName, String codeURL, String language, String algorithmName) {
        this.implName = implName;
        this.codeURL = codeURL;
        this.language = language;
        this.algorithmName = algorithmName;
        this.benchmarks = new ArrayList<>();
    }
    public Implementation(String implName, String codeURL, String language, String algorithmName, List<Benchmark> benchmarks) {
        this.implName = implName;
        this.codeURL = codeURL;
        this.language = language;
        this.algorithmName = algorithmName;
        this.benchmarks = benchmarks;
    }

    //Getter and setter methods
    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
    }

    public String getCodeURL() {
        return codeURL;
    }

    public void setCodeURL(String codeURL) {
        this.codeURL = codeURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public List<Benchmark> getBenchmark() {
        return benchmarks;
    }

    public void setBenchmark(List<Benchmark> benchmarks) {
        this.benchmarks = benchmarks;
    }

    //Other methods
    public boolean addBenchmark(Benchmark b){
        return benchmarks.add(b);
    }
    public boolean removeBenchmark(String benName){
        return benchmarks.removeIf(b -> b.getBenchName().equals(benName));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Implementation)) return false;
        Implementation i = (Implementation) obj;
        return this.implNamesMatch(i) && this.codeURLsMatch(i) && this.languagesMatch(i)
                && this.parentAlgorithmsMatch(i) && this.benchmarksMatch(i);
    }

    /*
    * Checks if this implementation's benchmarks match imp's benchmarks exactly(order doesn't matter)
     */
    private boolean benchmarksMatch(Implementation imp) {
        //prevents null pointers
        if(this.benchmarks != null){
            //      VVVVV prevents null pointers
            if(imp.benchmarks != null && this.benchmarks.size() == imp.benchmarks.size()){
                //go through each benchmark in this and make sure imp has it also
                for(int i = 0; i < imp.benchmarks.size(); i++){
                    if(!this.benchmarks.contains(imp.benchmarks.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either imp has null benchmarks(and this does not) or the sizes don't match
                return false;
            }
        }else{
            //this.benchmarks is null so imp.benchmarks must be null
            return imp.benchmarks == null;
        }
    }

    /*
     *Check to make sure the parent names match
     */
    private boolean parentAlgorithmsMatch(Implementation i) {
        //the if statement prevents null pointers
        if(this.algorithmName != null){
            return this.algorithmName.equals(i.algorithmName);
        }else{
            return i.algorithmName == null;
        }
    }

    /*
     *Check to make sure the languages match
     */
    private boolean languagesMatch(Implementation i) {
        //the if statement prevents null pointers
        if(this.language != null){
            return this.language.equals(i.language);
        }else{
            return i.language == null;
        }
    }

    /*
     *Check to make sure the URLs match
     */
    private boolean codeURLsMatch(Implementation i) {
        //the if statement prevents null pointers
        if(this.codeURL != null){
            return this.codeURL.equals(i.codeURL);
        }else{
            return i.codeURL == null;
        }
    }

    /*
     *Check to make sure the implementation names match
     */
    private boolean implNamesMatch(Implementation i) {
        //the if statement prevents null pointers
        if(this.implName != null){
            return this.implName.equals(i.implName);
        }else{
            return i.implName == null;
        }
    }
}
