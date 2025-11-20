package com.codedifferently.lesson25.library.user;

public class LibraryUserModel {
  private final String id;
  private final String email;
  private final String firstName;
  private final String lastName;
  private final String passwordHash;

  public LibraryUserModel(String id, String email, String firstName, String lastName, String passwordHash) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.passwordHash = passwordHash;
  }

  public String getId() { return id; }
  public String getEmail() { return email; }
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public String getPasswordHash() { return passwordHash; }
}
