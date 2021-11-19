package CreateBenchmark;

import java.sql.Date;

public class CreateBenchmarkRequest {
    public String benchID;
    public String benchName;
    public String algoName;
    public String machineConfigName;
    public String implName;
    public String problemInstanceName;
    public Date dateRun;
    public long timeToRun;
    public CreateBenchmarkRequest(){

    }
    public CreateBenchmarkRequest(String benchID, String benchName, long timeToRun, Date dateRun, String algoName,String implName , String machineConfigName, String problemInstanceName){
        this.benchID = benchID;
        this.benchName = benchName;
        this.dateRun = dateRun;
        this.timeToRun =timeToRun;
        this.algoName = algoName;
        this.problemInstanceName =problemInstanceName;
        this.machineConfigName =machineConfigName;
        this.implName = implName;
    }

    public String getBenchID() {
        return benchID;
    }

    public void setBenchID(String benchID) {
        this.benchID = benchID;
    }

    public String getBenchName() {
        return benchName;
    }

    public void setBenchName(String benchName) {
        this.benchName = benchName;
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

    public String getProblemInstanceName() {
        return problemInstanceName;
    }

    public void setProblemInstanceName(String problemInstanceName) {
        this.problemInstanceName = problemInstanceName;
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
        return "CreateBenchmark(" + benchID + ", " + benchName + ", " + timeToRun + ", " + dateRun + ", " + algoName + ", " + implName+ ", " + machineConfigName +", " + problemInstanceName +")";
    }




}
