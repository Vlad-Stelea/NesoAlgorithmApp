package CreateBenchmark;

import com.google.gson.Gson;

import java.sql.Date;

public class CreateBenchmarkRequest {
    public String benchmarkName;
    public String algoName;
    public String machineConfigName;
    public String implName;
    public String probInstanceUUID;
    public Date dateRun;
    public long timeToRun;
    public CreateBenchmarkRequest(){

    }
    public CreateBenchmarkRequest(String benchmarkName, long timeToRun, Date dateRun, String algoName, String implName, String machineConfigName, String probInstanceUUID){
        this.benchmarkName = benchmarkName;
        this.dateRun = dateRun;
        this.timeToRun =timeToRun;
        this.algoName = algoName;
        this.probInstanceUUID =probInstanceUUID;
        this.machineConfigName =machineConfigName;
        this.implName = implName;
    }

    public String getBenchmarkName() {
        return benchmarkName;
    }

    public void setBenchmarkName(String benchmarkName) {
        this.benchmarkName = benchmarkName;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getMachineConfigName() {
        return machineConfigName;
    }

    public void setMachineConfigName(String machineConfigName) {
        this.machineConfigName = machineConfigName;
    }

    public String getImplName() {
        return implName;
    }

    public void setImplName(String implName) {
        this.implName = implName;
    }

    public String getProbInstanceUUID() {
        return probInstanceUUID;
    }

    public void setProbInstanceUUID(String probInstanceUUID) {
        this.probInstanceUUID = probInstanceUUID;
    }

    public Date getDateRun() {
        return dateRun;
    }

    public void setDateRun(Date dateRun) {
        this.dateRun = dateRun;
    }

    public long getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(long timeToRun) {
        this.timeToRun = timeToRun;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
