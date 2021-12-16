package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements IUsersDAO {

    private Connection conn;

    public UsersDAO() {
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

    @Override
    public List<String> getRegisteredUsers() throws SQLException {
        String statement = "SELECT DISTINCT username FROM activityLog;";
        PreparedStatement ps = conn.prepareStatement(statement);
        ResultSet rs = ps.executeQuery();
        List<String> users = new ArrayList<>();
        while(rs.next()) {
            String username = rs.getString("username");
            if (username != null) users.add(username);
        }
        return users;
    }

    @Override
    public void deleteUser(String username) throws SQLException {
        String statement = "DELETE FROM activityLog WHERE username = ?;";
        PreparedStatement ps = conn.prepareStatement(statement);
        ps.setString(1, username);
        ps.executeUpdate();
    }
}
