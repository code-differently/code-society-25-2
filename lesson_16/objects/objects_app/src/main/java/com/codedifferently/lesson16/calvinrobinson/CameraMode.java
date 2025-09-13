package com.codedifferently.lesson16.calvinrobinson;

/** Enum representing different camera shooting modes */
public enum CameraMode {
  AUTO("Automatic mode - camera selects settings"),
  MANUAL("Manual mode - user controls all settings"),
  PORTRAIT("Portrait mode - optimized for people photography"),
  LANDSCAPE("Landscape mode - optimized for scenery"),
  SPORTS("Sports mode - fast shutter for action shots"),
  NIGHT("Night mode - optimized for low light conditions"),
  MACRO("Macro mode - for close-up photography");

  private final String description;

  CameraMode(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
