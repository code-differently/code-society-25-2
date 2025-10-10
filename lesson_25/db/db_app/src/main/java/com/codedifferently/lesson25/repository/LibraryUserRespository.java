import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LibraryUserRespository {
    private final Database database;

    public LibraryUserRespository(Database database){
        this.database = database;
    }
}

private LibraryUserModel mapResultSetToUserModel(ResultSet resultSet) throws Exception {
        return new LibraryUserModel( 
            result.Set.getString("id"),
            result.Set.getString("email"),
            result.Set.getString("first_name"),
            result.Set.getString("last_name"),
            result.Set.getString("password")
        );
}

public List<LibraryUserModel> loadAllUsers() {
        String sql = "SELECT id, email, first_name, last_name, password FROM library_users";
        List<LibraryUserModel> users = new ArrayList<>();

        database.executeQuery(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    users.add(mapResultSetToUserModel(resultSet));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return users;
    }
