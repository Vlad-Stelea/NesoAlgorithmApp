package db;

import entities.MachineConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineConfigurationDAO {

    private Connection conn;

    public MachineConfigurationDAO() {
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

    public boolean createMachineConfiguration(String machineConfigName, int l1Cache, int l2Cache, String chip, int threads) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO machineConfiguration (machineConfigName, l1Cache, l2Cache, chip, threads) VALUES (?, ?, ?, ?, ?);");
        ps.setString(1, machineConfigName);
        ps.setInt(2, l1Cache);
        ps.setInt(3, l2Cache);
        ps.setString(4, chip);
        ps.setInt(5, threads);
        ps.execute();

        return true;
    }

    public MachineConfiguration getMachineConfiguration(String machineConfigUUID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM machineConfiguration WHERE machineConfigUUID = ?;");
        ps.setString(1, machineConfigUUID);
        ResultSet rs = ps.executeQuery();

        return generateMachineConfiguration(rs);
    }

    public List<MachineConfiguration> getAllMachineConfigurations() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM machineConfiguration;");
        ResultSet rs = ps.executeQuery();

        return generateMachineConfigurations(rs);
    }

    public boolean removeMachineConfiguration(String machineConfigName) throws SQLException {
        // make sure the Machine Configuration exists first
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM machineConfiguration WHERE machineConfigName = ?;");
        ps.setString(1, machineConfigName);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM machineConfiguration WHERE machineConfigName = ?;");
            psDelete.setString(1, machineConfigName);
            psDelete.execute();

            return true;
        }

        return false;
    }

    private MachineConfiguration generateMachineConfiguration(ResultSet rs) throws SQLException {
        if(rs.next()) {
            String machineConfigName = rs.getString("machineConfigName");
            int l1Cache = rs.getInt("l1Cache");
            int l2Cache = rs.getInt("l2Cache");
            String chip = rs.getString("chip");
            int threads = rs.getInt("threads");

            return new MachineConfiguration(machineConfigName, l1Cache, l2Cache, chip, threads);
        }

        return null;
    }

    private List<MachineConfiguration> generateMachineConfigurations(ResultSet rs) throws SQLException {
        List<MachineConfiguration> result = new ArrayList<>();

        while(rs.next()) {
            String machineConfigName = rs.getString("machineConfigName");
            int l1Cache = rs.getInt("l1Cache");
            int l2Cache = rs.getInt("l2Cache");
            String chip = rs.getString("chip");
            int threads = rs.getInt("threads");

            result.add(new MachineConfiguration(machineConfigName, l1Cache, l2Cache, chip, threads));
        }

        return result;
    }

}
