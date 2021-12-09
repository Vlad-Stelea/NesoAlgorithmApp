package LogUserAction;

import db.IActivityLogDAO;
import entities.PrettyDate;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class LogUserActionHandler {
    IActivityLogDAO activityLogDAO;

    public LogUserActionHandler(IActivityLogDAO activityLogDAO) {
        this.activityLogDAO = activityLogDAO;
    }

    public LogUserActionResponse handle(LogUserActionRequest request) {
        // instantiate all the params to store
        String uuid = UUID.randomUUID().toString();
        String username = request.username;
        String action = request.action;
        // Creates a date at the current system time
        Date date = new Date();
        // Store params
        try{
            boolean success = activityLogDAO.createUserAction(uuid, username, action, date);
            if(success) return new LogUserActionResponse(200, uuid, username, action, new PrettyDate(date));
            else return new LogUserActionResponse(400, "Error: Unknown error occurred");
        } catch (SQLException e) {
            return new LogUserActionResponse(400, "Error: Unknown error occurred");
        }
    }
}
