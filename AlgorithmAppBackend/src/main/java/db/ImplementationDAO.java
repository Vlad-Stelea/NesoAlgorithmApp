package java.db;

import java.entities.Implemenation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Implementation {
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

    public boolean createImplementation(String impName,String codeURL, String langauge,String algoName){
        PreparedStatement ps = conn.prepareStatement("INSERT INTO implementation (implName, codeURL, language, algoName) VALUES (?, ?, ?, ?);");
        ps.setString(1, impName);
        ps.setString(2, codeURL);
        ps.setString(3, langauge);
        ps.setString(4, algoName);
        ps.execute();
        return true;
    }



    public Implementation getImplementation(String implName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ?;");
        ps.setString(1, ImplName);
        ResultSet rs = ps.executeQuery();

        return generateImplementation(rs);
    }

    // could be useful for merging; will have to test, as we might get foreign key errors when deleting a className
    public boolean removeImplementation(String impName) throws SQLException {
        // make sure the Classification exists first
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM implementation WHERE implName = ?;");
        ps.setString(1, ImplName);
        ResultSet rs = ps.executeQuery();
        //delete all benchmarks
        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM benchmark WHERE implName = ?;");
            psDelete.setString(1, rs.getString(benchmarkName);
            psDelete.execute();

            return true;
        }

        return false;
    }

    private Implementation generateImplementationn(ResultSet rs) throws SQLException {
        String impName = rs.getString("impName");
        String codeURL = rs.getString("codeURL");
        String language = rs.getString("langauge");

        //TODO: see if its acceptable to return all new implementation with no children
        return new Implementation(impName, codeURL,langauge);
    }
}
