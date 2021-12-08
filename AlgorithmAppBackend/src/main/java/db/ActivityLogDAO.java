package db;

import Utils.DateAndTimeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
