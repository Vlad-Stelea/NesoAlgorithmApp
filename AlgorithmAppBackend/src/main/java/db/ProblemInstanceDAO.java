package db;

import entities.ProblemInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemInstanceDAO {

    private Connection conn;

    public ProblemInstanceDAO() {
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

    public boolean createProblemInstance(String probInstanceUUID, String probInstanceName, String datasetURL, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO problemInstance (probInstanceUUID, probInstanceName, datasetURL, algoName) VALUES (?, ?, ?, ?);");
        ps.setString(1, probInstanceUUID);
        ps.setString(2, probInstanceName);
        ps.setString(3, datasetURL);
        ps.setString(4, algoName);
        ps.execute();

        return true;
    }

    public ProblemInstance getProblemInstance(String probInstanceUUID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM problemInstance WHERE probInstanceUUID = ?;");
        ps.setString(1, probInstanceUUID);
        ResultSet rs = ps.executeQuery();

        return generateProblemInstance(rs);
    }

    public ArrayList<ProblemInstance> getAllProblemInstances() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM problemInstance ");
        ResultSet rs = ps.executeQuery();

        return generateProblemInstances(rs);
    }

    public ArrayList<ProblemInstance> getAllAlgosProblemInstances(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM problemInstance WHERE algoName = ?");
        ps.setString(1, algoName);
        ResultSet rs = ps.executeQuery();

        return generateProblemInstances(rs);
    }

    public boolean removeProblemInstance(String probInstanceUUID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM problemInstance WHERE probInstanceUUID = ?;");
        ps.setString(1, probInstanceUUID);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM problemInstance WHERE probInstanceUUID = ?;");
            psDelete.setString(1, probInstanceUUID);
            psDelete.execute();

            return true;
        }

        return false;
    }

    private ProblemInstance generateProblemInstance(ResultSet rs) throws SQLException {
        if(!rs.next()) { return null; }

        String probInstanceUUID = rs.getString("probInstanceUUID");
        String probInstanceName = rs.getString("probInstanceName");
        String datasetURL = rs.getString("datasetURL");
        String algoName = rs.getString("algoName");

        return new ProblemInstance(probInstanceUUID, probInstanceName, datasetURL, algoName);
    }

    private ArrayList<ProblemInstance> generateProblemInstances(ResultSet rs) throws SQLException {
        ArrayList<ProblemInstance> ret = new ArrayList<>();

        while(rs.next()) {
            String probInstanceUUID = rs.getString("probInstanceUUID");
            String probInstanceName = rs.getString("probInstanceName");
            String datasetURL = rs.getString("datasetURL");
            String algoName = rs.getString("algoName");

            ret.add(new ProblemInstance(probInstanceUUID, probInstanceName, datasetURL, algoName));

        }
        return ret;
    }

}
