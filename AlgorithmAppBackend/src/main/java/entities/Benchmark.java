package entities;

public class Benchmark {

    public String getBenchName() {
        return benchName;
    }

    public void setBenchName(String benchName) {
        this.benchName = benchName;
    }

    private String benchName;

    public Benchmark(String benchName){
        this.benchName = benchName;
    }


}
