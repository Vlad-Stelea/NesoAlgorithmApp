package db;

import java.sql.SQLException;
import java.util.List;

public interface IUsersDAO {
    List<String> getRegisteredUsers() throws SQLException;
}
