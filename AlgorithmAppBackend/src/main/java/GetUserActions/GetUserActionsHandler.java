package GetUserActions;

import db.ActivityLogDAO;
import entities.UserAction;

import java.util.List;

public class GetUserActionsHandler {
    ActivityLogDAO activityLogDAO;

    public GetUserActionsHandler(ActivityLogDAO dao){
        this.activityLogDAO = dao;

    }

    public GetUserActionsResponse handle() {
        GetUserActionsResponse response;

        try{
            List<UserAction> userActionList = activityLogDAO.getAllUserAction();

            response = new GetUserActionsResponse(userActionList,200);

        } catch (Exception e){

            e.printStackTrace();
            response = new GetUserActionsResponse(400, "Unable to get userActions");
        }
        return response;
    }

}
