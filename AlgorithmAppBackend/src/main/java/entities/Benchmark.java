package entities;

import java.sql.Date;

public class Benchmark {


    private String benchID;
    private String benchName;
    private String algoName;
    private String machineConfigName;
    private String implName;
    private String problemInstanceName;
    private Date dateRun;
    private long timeToRun;



    public Benchmark(String benchID, String benchName, long timeToRun, Date dateRun, String algoName,String implName , String machincConfigName, String problemInstanceName){
        this.benchID = benchID;
        this.benchName = benchName;
        this.dateRun = dateRun;
        this.timeToRun =timeToRun;
        this.algoName = algoName;
        this.problemInstanceName =problemInstanceName;
        this.machineConfigName =machineConfigName;
        this.implName = implName;
    }



    //Getters and Setters
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

    public String getProblemInstanceName() {
        return problemInstanceName;
    }

    public void setProblemInstanceName(String problemInstanceName) {
        this.problemInstanceName = problemInstanceName;
    }

    public long getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(long timeToRun) {
        this.timeToRun = timeToRun;
    }

    public Date getDateRun() {
        return dateRun;
    }

    public void setDateRun(Date dateRun) {
        this.dateRun = dateRun;
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
}
