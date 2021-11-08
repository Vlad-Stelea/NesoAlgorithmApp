
package entities;
import java.util.ArrayList;
import java.util.List;

public class Implementation {

    private String implName;
    private String codeURL;
    private String language;
    private Algorithm algorithmName;
    private ArrayList<Benchmark> benchmark;


    public Implementation(String implName, String codeURL,String language,Algorithm algorithmName) {
        this.implName = implName;
        this.codeURL = codeURL;
        this.language = language;
        this.benchmark = new ArrayList<>();
    }
    public Implementation(String implName, String codeURL,String language,Algorithm algorithmName,ArrayList<Benchmark> benchmark) {
        this.implName = implName;
        this.codeURL = codeURL;
        this.language = language;
        this.benchmark = benchmark;
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

    public ArrayList<Benchmark> getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(ArrayList<Benchmark> benchmark) {
        this.benchmark = benchmark;
    }

    //Other methods
    public boolean addBenchmark(Benchmark b){
        return benchmark.add(b);
    }
    public boolean removeBenchmark(String benName){
        return benchmark.removeIf(b -> b.getBenchName().equals(benName));
    }




}


