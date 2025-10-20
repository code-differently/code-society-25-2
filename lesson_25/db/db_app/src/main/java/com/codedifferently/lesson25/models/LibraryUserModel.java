package com.codedifferently.lesson25.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_users")
public class LibraryUserModel {

  @Id 
  public String id;
  
  @Column(unique = true, nullable = false)
  public String email;
  
  @Column(name = "first_name", nullable = false)
  public String firstName;
  
  @Column(name = "last_name", nullable = false)
  public String lastName;
  
  @Column(name = "password_hash", nullable = false)
  public String passwordHash;
}
