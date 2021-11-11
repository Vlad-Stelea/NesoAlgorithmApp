package db;

import entities.Algorithm;
import entities.Classification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlgorithmDAO {
    private Connection conn;

    public AlgorithmDAO() {
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

    public boolean createAlgorithm(String algoName, String parentClassName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO algorithm (algoName, className) VALUES (?, ?);");
        ps.setString(1, algoName);
        ps.setString(2, parentClassName);
        ps.execute();

        return true;
    }

    public Algorithm getAlgorithm(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM algorithm WHERE algoName = ?;");
        ps.setString(1, algoName);
        ResultSet rs = ps.executeQuery();

        return generateBasicAlgorithm(rs);
    }

    public ArrayList<Algorithm> getAllAlgorithms() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM algorithm ");
        ResultSet rs = ps.executeQuery();


        return generateBasicAlgorithms(rs);
    }

    public boolean removeAlgorithm(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM algorithm WHERE algoName = ?;");
        ps.setString(1, algoName);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM algorithm WHERE algoName = ?;");
            psDelete.setString(1, algoName);
            psDelete.execute();

            return true;
        }

        return false;
    }

    public boolean removeAlgorithmsAndChildren(String algoName) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM algorithm WHERE algoName = ?;");
        ps.setString(1, algoName);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            Algorithm a = generateFullAlgorithm(rs);
            //TODO? delete children iplementations, benchmarks, ect.(we might also just do this in the handler idk

            PreparedStatement psDeleteAlgos = conn.prepareStatement("DELETE FROM algorithm WHERE algoName = ?;");
            psDeleteAlgos.setString(1, algoName);
            psDeleteAlgos.execute();

            return true;
        }

        return false;
    }

    private Algorithm generateBasicAlgorithm(ResultSet rs) throws SQLException {
        if(!rs.next()){
            return null;
        }
        String algoName = rs.getString("algoName");
        String parentClassName = rs.getString("className");

        return new Algorithm(algoName, parentClassName);
    }

    private ArrayList<Algorithm> generateBasicAlgorithms(ResultSet rs) throws SQLException {
        ArrayList<Algorithm> ret = new ArrayList<>();
        while(rs.next()) {
            String algoName = rs.getString("algoName");
            String parentClassName = rs.getString("className");

            ret.add(new Algorithm(algoName, parentClassName));

        }
        return ret;
    }

    private Algorithm generateFullAlgorithm(ResultSet rs) throws SQLException {
        if(!rs.next()){
            return null;
        }
        String algoName = rs.getString("algoName");
        String parentClassName = rs.getString("className");

        return new Algorithm(algoName, parentClassName);

    }

}