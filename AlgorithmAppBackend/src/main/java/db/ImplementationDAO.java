package db;

import entities.Algorithm;
import entities.Implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImplementationDAO {
    private Connection conn;
    private AlgorithmDAO algoConn = new AlgorithmDAO();
    public ImplementationDAO() {
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

    public boolean createImplementation(String implName,String codeURL, String language,String algoName) throws SQLException{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO implementation (implName, codeURL, language, algoName) VALUES (?, ?, ?, ?);");
            ps.setString(1, implName);
            ps.setString(2, codeURL);
            ps.setString(3, language);
            ps.setString(4, algoName);
            ps.execute();
        return true;
    }



    public Implementation getImplementation(String implName, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ? AND algoName = ?;");
        ps.setString(1, implName);
        ps.setString(2, algoName);
        ResultSet rs = ps.executeQuery();

        return generateImplementation(rs);
    }

    public ArrayList<Implementation> getAllImplementation() throws SQLException{
        ArrayList imps = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation;");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            imps.add(new Implementation(rs.getString("implName"),rs.getString("codeURL"),rs.getString("language"),new Algorithm(rs.getString("algoName"))));
        }

        return imps;

    }


    public boolean removeImplementation(String implName, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ? AND algoName = ?;");
        ps.setString(1, implName);
        ps.setString(2, algoName);
        ResultSet rs = ps.executeQuery();
        //delete all benchmarks
        /**if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM benchmark WHERE implName = ?;");
            psDelete.setString(1, rs.getString(benchmarkName);
            psDelete.execute();

            return true;
        }*/

        return false;
    }

    public ArrayList<Implementation> getImplementationForAlgo(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE algoName = ?;");
        ps.setString(1, algoName);
        ArrayList imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            imps.add(new Implementation(rs.getString("implName"),rs.getString("codeURL"),rs.getString("language"),new Algorithm(algoName)));
        }

        return imps;
    }

    private Implementation generateImplementation(ResultSet rs) throws SQLException {
        String implName = rs.getString("implName");
        String codeURL = rs.getString("codeURL");
        String language = rs.getString("language");
        Algorithm algo = new Algorithm(rs.getString("algoName"));

        //TODO: see if its acceptable to return all new implementation with no children
        return new Implementation(implName, codeURL,language,algo);
    }
}
