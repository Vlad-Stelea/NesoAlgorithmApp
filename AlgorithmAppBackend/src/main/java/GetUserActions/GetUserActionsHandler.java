package GetUserActions;

import db.ActivityLogDAO;
import entities.UserAction;

import java.util.List;

public class GetUserActionsHandler {
    ActivityLogDAO activityLogDAO;

    public GetUserActionsHandler(ActivityLogDAO dao){
        this.activityLogDAO = dao;

    }

    public GetUserActionsResponse handle(GetUserActionsRequest request) {
        GetUserActionsResponse response;
        try{
            List<UserAction> userActionList = activityLogDAO.getUserAction(request.getUsername());

            response = new GetUserActionsResponse(userActionList, request.getUsername(), 200);

        } catch (Exception e){

            e.printStackTrace();
            response = new GetUserActionsResponse(400, "Unable to get userActions\n" + e);
        }
        return response;
    }

}
