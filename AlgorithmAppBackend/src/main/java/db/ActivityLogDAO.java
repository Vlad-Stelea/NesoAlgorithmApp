package db;

import Utils.DateAndTimeUtils;
import entities.Algorithm;
import entities.UserAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ActivityLogDAO implements IActivityLogDAO{
    private Connection conn;

    public ActivityLogDAO() {
        try {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
            e.printStackTrace();
        }
    }

    @Override
    public boolean createUserAction(String activityLogUUID, String username, String action, Date date) throws SQLException {
        String query = "INSERT INTO activityLog (activityLogUUID, username, action, date) VALUES (?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, activityLogUUID);
        ps.setString(2, username);
        ps.setString(3, action);
        ps.setTimestamp(4, DateAndTimeUtils.convertUtilDateToSqlTimeStamp(date));
        ps.execute();

        return true;
    }


    public ArrayList<UserAction> getUserAction(String userName) throws SQLException {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM activityLog WHERE username = ?");
            ps.setString(1,userName);
            ResultSet rs = ps.executeQuery();


            return generateUserAction(rs);
    }


    private ArrayList<UserAction> generateUserAction(ResultSet rs) throws SQLException {
        ArrayList<UserAction> ret = new ArrayList<>();
        while(rs.next()) {
            String activityLogUUID = rs.getString("activityLogUUID");
            String username = rs.getString("username");
            String action = rs.getString("action");
            Date date = rs.getDate("date");

            ret.add(new UserAction(activityLogUUID, username,action,date));

        }
        return ret;
    }


    }
