package db;

import entities.Classification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificationDAO {
    private Connection conn;
    private AlgorithmDAO adao = new AlgorithmDAO();
    public ClassificationDAO() {
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

    public boolean createClassification(String className, String parentClassName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO classification (className, parentClassName) VALUES (?, ?);");
        ps.setString(1, className);
        ps.setString(2, parentClassName);
        ps.execute();

        return true;
    }

    public boolean checkClassificationExists(String className) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM classification WHERE className = ?;");
        ps.setString(1, className);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    public Classification getClassification(String className) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM classification WHERE className = ?;");
        ps.setString(1, className);
        ResultSet rs = ps.executeQuery();

        return generateClassification(rs);
    }

    public List<Classification> getAllClassifications() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM classification;");
        ResultSet rs = ps.executeQuery();

        return generateClassifications(rs);
    }

    // could be useful for merging; will have to test, as we might get foreign key errors when deleting a className
    public boolean removeClassification(String className) throws SQLException {
        // make sure the Classification exists first
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM classification WHERE className = ?;");
        ps.setString(1, className);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM classification WHERE className = ?;");
            psDelete.setString(1, className);
            psDelete.execute();

            return true;
        }

        return false;
    }


    private Classification generateClassification(ResultSet rs) throws SQLException {
        if(rs.next()) {
            String className = rs.getString("className");
            String parentClassName = rs.getString("parentClassName");

            // NOTE With the way things are currently set up, we'll be returning Classifications with null parents.
            //      Would it be better to match database structure and set the Classification entity to have a parent name String?
            //      Or is having this flexibility easier for potential future applications? I'm inclined to keep it this way,
            //      it just seems a bit janky.
            return new Classification(className, parentClassName);
        }

        return null;
    }

    private List<Classification> generateClassifications(ResultSet rs) throws SQLException {
        List<Classification> result = new ArrayList<>();

        while(rs.next()) {
            String className = rs.getString("className");
            String parentClassName = rs.getString("parentClassName");

            result.add(new Classification(className, parentClassName));
        }

        return result;
    }

    public boolean updateClassification(String Name, String parentName) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE classification SET parentClassName = ? WHERE className = ?;");
        ps.setString(1, parentName);
        ps.setString(2, Name);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return true;
        }
        return false;
    }



    public boolean mergeClassification(String Name,String newParentName) throws SQLException{

            PreparedStatement updatePS = conn.prepareStatement("UPDATE classification SET parentClassName = ? WHERE parentClassName = ?;");
            updatePS.setString(1, newParentName);
            updatePS.setString(2, Name);
            updatePS.executeUpdate();


        PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM algorithm WHERE className = ?;");
        ps2.setString(1, Name);
        ResultSet rs2 = ps2.executeQuery();
        while(rs2.next()) {
            adao.reclassifyAlgorithm(rs2.getString("algoName"),newParentName);
        }


        return true;
    }



}
