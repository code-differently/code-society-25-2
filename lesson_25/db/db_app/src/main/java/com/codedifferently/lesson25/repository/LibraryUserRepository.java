package com.codedifferently.lesson25.repository;

import com.codedifferently.lesson25.models.LibraryUserModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryUserRespository {
    private final Database database;

    public LibraryUserRespository(Database database){
        this.database = database;
    }

    private LibraryUserModel mapResultSetToUserModel(ResultSet resultSet) throws Exception {
        return new LibraryUserModel( 
            resultSet.getString("id"),
            resultSet.getString("email"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("password")
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
}