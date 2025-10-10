package com.codedifferently.lesson25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "library_users")
public class LibraryUsersModel {

  @Id
  public UUID id;
  public String email;
  public String firstName;
  public String lastName;
  public String passwordHash;

}
/*
 * 
 * package com.codedifferently.lesson25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "guests")
public class LibraryGuestModel {

  public String type;
  public String name;
  @Id public String email;

  @OneToMany(mappedBy = "email", fetch = FetchType.EAGER)
  public List<CheckoutModel> checkedOutItems;
}

 */