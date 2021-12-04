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

    public boolean removeBenchmark(String benchmarkID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE benchmarkUUID = ?;");
        ps.setString(1, benchmarkID);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM benchmark WHERE benchmarkUUID = ?;");
            psDelete.setString(1, benchmarkID);
            psDelete.execute();

            return true;
        }
        else {
            return false;
        }

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

        ResultSet rs = ps.executeQuery();
        return generateArrayOfBenchmark(rs);
    }
    public ArrayList<Benchmark> getBenchmarkForImp(String ImpName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE implName = ?;");
        ps.setString(1, ImpName);

        ResultSet rs = ps.executeQuery();
        return generateArrayOfBenchmark(rs);
    }
    public ArrayList<Benchmark> getBenchmarkForMachCong(String machineConfig) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE machineConfigUUID = ?;");
        ps.setString(1, machineConfig);

        ResultSet rs = ps.executeQuery();
        return generateArrayOfBenchmark(rs);
    }
    public ArrayList<Benchmark> getBenchmarkForProbInst(String problemInstance) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE probInstanceUUID = ?;");
        ps.setString(1, problemInstance);

        ResultSet rs = ps.executeQuery();
        return generateArrayOfBenchmark(rs);
    }

    public boolean removeBenchmarksByImplName(String implName, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE implName = ? AND algoName = ?;");
        ps.setString(1, implName);
        ps.setString(2, algoName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM benchmark WHERE implName = ? AND algoName = ?;");
            psDelete.setString(1, implName);
            psDelete.setString(2, algoName);

            psDelete.execute();
        }

        return true;
    }

    public ArrayList<Benchmark> generateArrayOfBenchmark(ResultSet rs) throws SQLException{

        ArrayList<Benchmark> benchmarks = new ArrayList<>();
        while(rs.next()) {
            benchmarks.add(new Benchmark(rs.getString("benchmarkUUID"),
                    rs.getString("benchmarkName"),
                    rs.getLong("timeToRun"),
                    rs.getDate("dateRun"),
                    rs.getString("algoName") ,
                    rs.getString("implName"),
                    rs.getString("machineConfigUUID"),
                    rs.getString("probInstanceUUID")));
        }
        return benchmarks;

    }

    public boolean removeBenchmarksByProbInstanceUUID(String probInstanceUUID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM benchmark WHERE probInstanceUUID = ?;");
        ps.setString(1, probInstanceUUID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM benchmark WHERE probInstanceUUID = ?;");
            psDelete.setString(1, probInstanceUUID);

            psDelete.execute();
        }

        return true;
    }

    public Benchmark generateBenchmark(ResultSet rs) throws SQLException {
        if(!rs.next()) {
            return null;
        }
        String benchID = rs.getString("benchmarkUUID");
        String benchName =rs.getString("benchmarkName");
        String algoName =rs.getString("algoName");
        String machineConfigName =rs.getString("machineConfigUUID");
        String implName = rs.getString("implName");
        String problemInstanceName =rs.getString("probInstanceUUID");
        Date dateRun =rs.getDate("dateRun");
        long timeToRun =rs.getLong("timeToRun");

        return new Benchmark(benchID, benchName,timeToRun,dateRun,algoName,implName,machineConfigName,problemInstanceName);

    }

}
