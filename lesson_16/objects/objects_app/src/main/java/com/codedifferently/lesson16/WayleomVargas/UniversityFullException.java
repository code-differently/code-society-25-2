package com.codedifferently.lesson16.WayleomVargas;

public class UniversityFullException extends Exception {
  public UniversityFullException(String message) {
    super(message);
  }

  public UniversityFullException(String message, Throwable cause) {
    super(message, cause);
  }
}

class StudentNotFoundException extends Exception {
  public StudentNotFoundException(String message) {
    super(message);
  }

  public StudentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
