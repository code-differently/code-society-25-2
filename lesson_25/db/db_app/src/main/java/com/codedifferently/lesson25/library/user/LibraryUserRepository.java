package com.codedifferently.lesson25.library.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryUserRepository {
  private final Connection connection;

  public LibraryUserRepository(Connection connection) {
    this.connection = connection;
  }

  public List<LibraryUserModel> findAll() throws Exception {
    String sql = "SELECT id, email, first_name, last_name, password_hash FROM library_users";
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
      List<LibraryUserModel> users = new ArrayList<>();
      while (rs.next()) {
        users.add(new LibraryUserModel(
            rs.getString("id"),
            rs.getString("email"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("password_hash")
        ));
      }
      return users;
    }
  }
}
