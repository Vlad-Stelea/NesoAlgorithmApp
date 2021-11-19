package db;

import entities.Benchmark;

import java.sql.*;
import java.util.ArrayList;

public class BenchmarkDAO {

    private Connection conn;

    public BenchmarkDAO() {
        try {
            conn = DatabaseUtil.connect();
            System.out.println("Connection successful!");

            PreparedStatement ps = conn.prepareStatement("SET SQL_SAFE_UPDATES = 0");
            ps.execute();
        }

        catch (Exception e) {
            conn = null;
            System.out.println("Connection has failed!");
        }
    }
    public boolean createBenchmark(String benchID,String benchName, long timeToRun, Date dateRun, String algoName, String problemInstanceName, String machineConfigName, String probInstanceName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO benchmark (benchmarkUUID,benchmarkName, timeToRun, dateRun, algoName, implName, machineConfigUUID,probInstanceUUID) VALUES (?, ?, ?, ?, ?, ?,);");
        ps.setString(1, benchID);
        ps.setString(2, benchName);
        ps.setLong(3, timeToRun);
        ps.setDate(4, dateRun);
        ps.setString(5, algoName);
        ps.setString(6, problemInstanceName);
        ps.setString(7, machineConfigName);
        ps.setString(8, probInstanceName);
        ps.execute();

        return true;
    }
    public Benchmark getBenchmark(String benchID) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE benchmarkUUID = ?;");
        ps.setString(1, benchID);
        ResultSet rs = ps.executeQuery();

        return generateBenchmark(rs);

    }


    public ArrayList<Benchmark> getBenchmarkForAlgo(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE algoName = ?;");
        ps.setString(1, algoName);
        ArrayList imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        imps = generateArrayOfBenchmark(rs);

        return imps;
    }
    public ArrayList<Benchmark> getBenchmarkForImp(String ImpName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE implName = ?;");
        ps.setString(1, ImpName);
        ArrayList imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        imps = generateArrayOfBenchmark(rs);

        return imps;
    }
    public ArrayList<Benchmark> getBenchmarkForMachCong(String machineConfig) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE machineConfigUUID = ?;");
        ps.setString(1, machineConfig);
        ArrayList imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        imps = generateArrayOfBenchmark(rs);

        return imps;
    }
    public ArrayList<Benchmark> getBenchmarkForProbInst(String problemInstance) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE probInstanceUUID = ?;");
        ps.setString(1, problemInstance);
        ArrayList imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        imps = generateArrayOfBenchmark(rs);

        return imps;
    }

    public ArrayList<Benchmark> generateArrayOfBenchmark(ResultSet rs) throws SQLException{

        ArrayList imps = new ArrayList<>();
        while(rs.next()) {
            imps.add(new Benchmark(rs.getString("benchmarkUUID"),rs.getString("benchmarkName"),rs.getLong("timeToRun"),rs.getDate("dateRun"),rs.getString("algoName") ,rs.getString("implName"),rs.getString("machineConfigUUID"),rs.getString("probInstanceUUID")));
        }
        return imps;

    }

    public Benchmark generateBenchmark(ResultSet rs) throws SQLException {
        if(!rs.next()) {
        return null;
        }
        String benchID = rs.getString("benchmarkUUID");
        String benchName =rs.getString("benchmarkName");
        String algoName =rs.getString("algoName");
        String machinceConfigName =rs.getString("machineConfigUUID");
        String implName = rs.getString("implName");
        String problemInstanceName =rs.getString("probInstanceUUID");
        Date dateRun =rs.getDate("dateRun");
       long timeToRun =rs.getLong("timeToRun");

        //TODO: see if its acceptable to return all new implementation with no children
        return new Benchmark(benchID, benchName,timeToRun,dateRun,algoName,implName,machinceConfigName,problemInstanceName);

    }



}
