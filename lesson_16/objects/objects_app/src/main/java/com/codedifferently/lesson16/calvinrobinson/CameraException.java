package com.codedifferently.lesson16.calvinrobinson;

/** Custom exception thrown when camera operations fail */
public class CameraException extends Exception {

  public CameraException(String message) {
    super(message);
  }

  public CameraException(String message, Throwable cause) {
    super(message, cause);
  }
}
