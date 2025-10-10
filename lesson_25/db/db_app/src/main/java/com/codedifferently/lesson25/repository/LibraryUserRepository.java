package com.codedifferently.lesson25.repository;

import com.codedifferently.lesson25.models.LibraryUserModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryUserRepository {
    private final Connection connection;

    public LibraryUserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<LibraryUserModel> findAll() throws Exception {
        List<LibraryUserModel> users = new ArrayList<>();
        String sql = "SELECT id, email, first_name, last_name, password FROM library_users";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new LibraryUserModel(
                    rs.getString("id"),
                    rs.getString("email"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("password")
                ));
            }
        }
        return users;
    }
}
