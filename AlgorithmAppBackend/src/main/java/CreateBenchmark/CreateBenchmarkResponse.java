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

    public CreateBenchmarkResponse (int code, String benchID, String benchName, String algoName, String machineConfigName, String implName, String problemInstanceName, Date dateRun) {
        this.benchID = benchID;
        this.benchName = benchName;
        this.algoName = algoName;
        this.machineConfigName = machineConfigName;
        this.implName = implName;
        this.problemInstanceName = problemInstanceName;
        this.dateRun = dateRun;
        this.httpCode = code;
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
            return otherResponse.statusCode == this.statusCode &&
                    equalOrBothNull(otherResponse.implName, this.implName) &&
                    equalOrBothNull(otherResponse.algoName, this.algoName) &&
                    equalOrBothNull(otherResponse.codeUrl, this.codeUrl) &&
                    equalOrBothNull(otherResponse.language, this.language) &&
                    equalOrBothNull(otherResponse.error, this.error);
        } else {
            return false;
        }
    }

}
