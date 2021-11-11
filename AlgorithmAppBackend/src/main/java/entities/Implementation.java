package entities;

import java.util.ArrayList;
import java.util.List;

public class Implementation {

    private String implName;
    private String codeURL;
    private String language;
    private Algorithm algorithmName;
    private List<Benchmark> benchmarks;

    public Implementation(String implName, String codeURL, String language, Algorithm algorithmName) {
        this.implName = implName;
        this.codeURL = codeURL;
        this.language = language;
        this.algorithmName = algorithmName;
        this.benchmarks = new ArrayList<>();
    }
    public Implementation(String implName, String codeURL, String language, Algorithm algorithmName, List<Benchmark> benchmarks) {
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

    public Algorithm getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(Algorithm algorithmName) {
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

    private boolean benchmarksMatch(Implementation imp) {
        if(this.benchmarks != null){
            if(imp.benchmarks != null && this.benchmarks.size() == imp.benchmarks.size()){
                for(int i = 0; i < imp.benchmarks.size(); i++){
                    if(!this.benchmarks.contains(imp.benchmarks.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either c has null benchmarks or the sizes don't match
                return false;
            }
        }else{
            return imp.benchmarks == null;
        }
    }

    private boolean parentAlgorithmsMatch(Implementation i) {
        //todo when we set parent to be a string implement this, if we implement now it will cause an infinite loop
        return true;
    }

    private boolean languagesMatch(Implementation i) {
        if(this.language != null){
            return this.language.equals(i.language);
        }else{
            return i.language == null;
        }
    }

    private boolean codeURLsMatch(Implementation i) {
        if(this.codeURL != null){
            return this.codeURL.equals(i.codeURL);
        }else{
            return i.codeURL == null;
        }
    }

    private boolean implNamesMatch(Implementation i) {
        if(this.implName != null){
            return this.implName.equals(i.implName);
        }else{
            return i.implName == null;
        }
    }
}
