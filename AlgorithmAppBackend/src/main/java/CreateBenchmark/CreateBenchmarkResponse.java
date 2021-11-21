package CreateBenchmark;


import com.google.gson.Gson;

import java.sql.Date;
import static Utils.EqualityUtils.equalOrBothNull;

public class CreateBenchmarkResponse {

    public final int httpCode;
    public final String error;
    String benchID;
    String benchName;
    String algoName;
    String machineConfigName;
    String implName;
    String problemInstanceName;
    Date dateRun;
    long timeToRun;
    public CreateBenchmarkResponse (int code, String benchID, String benchName, long timeToRun, Date dateRun, String algoName,String implName , String machineConfigName, String problemInstanceName) {
        this.benchID = benchID;
        this.benchName = benchName;
        this.algoName = algoName;
        this.machineConfigName = machineConfigName;
        this.implName = implName;
        this.problemInstanceName = problemInstanceName;
        this.dateRun = dateRun;
        this.httpCode = code;
        this.timeToRun = timeToRun;
        this.error = "";
    }

    // 200 means success
    public CreateBenchmarkResponse (int code, String error) {
        this.httpCode = code;
        this.error = error;
    }

    public String getBenchID() {
        return benchID;
    }

    public long getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(long timeToRun) {
        this.timeToRun = timeToRun;
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

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if(o instanceof CreateBenchmarkResponse) {
            CreateBenchmarkResponse otherResponse = (CreateBenchmarkResponse) o;
            return otherResponse.httpCode == this.httpCode &&
                    equalOrBothNull(otherResponse.benchID, this.benchID) &&
                    equalOrBothNull(otherResponse.benchName, this.benchName) &&
                    equalOrBothNull(otherResponse.algoName, this.algoName) &&
                    equalOrBothNull(otherResponse.timeToRun, this.timeToRun) &&
                    equalOrBothNull(otherResponse.machineConfigName, this.machineConfigName) &&
                    equalOrBothNull(otherResponse.implName, this.implName) &&
                    equalOrBothNull(otherResponse.problemInstanceName, this.problemInstanceName) &&
                    equalOrBothNull(otherResponse.dateRun, this.dateRun);
        } else {
            return false;
        }
    }

}
