package GetRegisteredUsers;

import db.IUsersDAO;

import java.sql.SQLException;
import java.util.List;

public class GetRegisteredUsersHandler {
    IUsersDAO usersDAO;

    public GetRegisteredUsersHandler(IUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public GetRegisteredUsersResponse handle(GetRegisteredUsersRequest request) {
        try {
            List<String> users = usersDAO.getRegisteredUsers();
            return new GetRegisteredUsersResponse(200, users);
        } catch (SQLException e) {
            return new GetRegisteredUsersResponse(400, e.getMessage());
        }
    }
}
