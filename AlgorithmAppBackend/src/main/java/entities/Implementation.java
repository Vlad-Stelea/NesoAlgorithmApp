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
}
