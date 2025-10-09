package com.codedifferently.lesson25.models;
    
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
    
    @Entity
    @Table(name = "library_users")
   public class LibraryUserModel { 

      @Id public Integer userId;
      public String emailAddress;
      public String firstName;
      public String lastName;
      public String password;
    
      @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER)
      public List<LibraryUserModel> LibraryUsers;
    }

