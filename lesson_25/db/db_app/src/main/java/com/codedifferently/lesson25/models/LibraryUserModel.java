package com.codedifferently.lesson25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "library_users")
public class LibraryUserModel {

  @Id public UUID id;
  public String email;
  public String firstName;
  public String lastName;
  public String passwordHash;
}
