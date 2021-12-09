package db;

import java.sql.SQLException;
import java.util.Date;

public interface IActivityLogDAO {
    boolean createUserAction(String activityLogUUID, String username, String action, Date date) throws SQLException;
}
