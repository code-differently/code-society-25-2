package com.codedifferently.lesson23.config;

import com.codedifferently.lesson23.library.Librarian;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Librarian librarian() {
    return new Librarian("System", "system@library.local");
  }
}
