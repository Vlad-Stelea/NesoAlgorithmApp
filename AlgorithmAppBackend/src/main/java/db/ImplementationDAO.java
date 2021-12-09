package db;

import entities.Implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ImplementationDAO {
    private Connection conn;

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

    public boolean hasImplementation(String implName, String algoName) throws SQLException{
        Optional<Implementation> impl = getImplementation(implName, algoName);
        return impl.isPresent();
    }

    public Optional<Implementation> getImplementation(String implName, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ? AND algoName = ?;");
        ps.setString(1, implName);
        ps.setString(2, algoName);
        ResultSet rs = ps.executeQuery();

        return generateImplementation(rs);
    }

    public ArrayList<Implementation> getAllImplementation() throws SQLException{
        ArrayList<Implementation> imps = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation;");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            imps.add(new Implementation(rs.getString("implName"),rs.getString("codeURL"),rs.getString("language"),rs.getString("algoName")));
        }

        return imps;

    }


    public boolean removeImplementation(String implName, String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ? AND algoName = ?;");
        ps.setString(1, implName);
        ps.setString(2, algoName);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM implementation WHERE implName = ? AND algoName = ?;");
            psDelete.setString(1, implName);
            psDelete.setString(2, algoName);
            psDelete.execute();

            return true;
        }

        return false;
    }

    public boolean removeImplementationsByAlgorithm(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE algoName = ?;");
        ps.setString(1, algoName);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM implementation WHERE algoName = ?;");
            psDelete.setString(1, algoName);
            psDelete.execute();

            return true;
        }

        return false;
    }

    public ArrayList<Implementation> getImplementationForAlgo(String algoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE algoName = ?;");
        ps.setString(1, algoName);
        ArrayList<Implementation> imps = new ArrayList<>();

        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            imps.add(new Implementation(rs.getString("implName"),rs.getString("codeURL"),rs.getString("language"), algoName));
        }

        return imps;
    }

    private Optional<Implementation> generateImplementation(ResultSet rs) throws SQLException {
        // Check if the ResultSet is filled already
        // Note, ResultSet must have not had any operations done on it before being passed into here
        if (!rs.isBeforeFirst()) return Optional.empty();
        if(!rs.next()) return Optional.empty();

        String implName = rs.getString("implName");
        String codeURL = rs.getString("codeURL");
        String language = rs.getString("language");
        String algo = rs.getString("algoName");

        //TODO: see if its acceptable to return all new implementation with no children
        return Optional.of(new Implementation(implName, codeURL,language,algo));
    }
}
