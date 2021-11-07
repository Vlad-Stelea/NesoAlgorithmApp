
package java.entities;
import java.util.ArrayList;
import java.util.List;

public class Implementation {

    private String name;
    private String codeURL;
    private String language;
    private String algorithmName;
    private List<Benchmarks> benchmark;


    public Implementation(String name, String codeURL,String language,String algorithmName) {
        this.name = name;
        this.codeURL = codeURL;
        this.language = language;
        this.benchmark = new ArrayList<>();
    }
    public Implementation(String name, String codeURL,String language,String algorithmName,ArrayList<Benchmarks> benchmark) {
        this.name = name;
        this.codeURL = codeURL;
        this.language = language;
        this.benchmark = benchmark;
    }
    //Getter and setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Benchmarks> getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(ArrayList<Benchmarks> benchmark) {
        this.benchmark = benchmark;
    }

    //Other methods
    public boolean addBenchmark(Benchmark b){
        return benchmark.add(b);
    }
    public boolean removeBenchmark(String benName){
        return benchmark.removeIf(b -> b.getBenchmarkName().equals(benName));
    }




}


