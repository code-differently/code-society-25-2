package com.codedifferently.lesson16.calvinrobinson;

/**
 * Demonstration class to showcase Camera functionality This demonstrates all required features in
 * action
 */
public class CameraDemo {

  public static void main(String[] args) {
    System.out.println("=== Camera Lesson 16 Demo ===\n");

    try {
      // Create a new camera instance
      Camera myCamera = new Camera("Canon", "EOS R5", 45.0, 3);

      // Display initial status
      System.out.println("Initial Camera Status:");
      System.out.println(myCamera.getCameraStatus());
      System.out.println();

      // Power on the camera
      System.out.println("Powering on camera...");
      myCamera.powerOn();
      System.out.println();

      // Change camera mode (uses enum)
      System.out.println("Changing camera modes:");
      myCamera.changeCameraMode(CameraMode.PORTRAIT);
      myCamera.changeCameraMode(CameraMode.SPORTS);
      System.out.println();

      // Take some photos (uses collection)
      System.out.println("Taking photos:");
      myCamera.takePhoto("Beautiful sunset");
      myCamera.takePhoto("Mountain landscape");
      myCamera.takePhoto("City skyline");
      System.out.println();

      // Display all photos (uses loop)
      myCamera.displayAllPhotos();

      // Show updated status
      System.out.println("Updated Camera Status:");
      System.out.println(myCamera.getCameraStatus());
      System.out.println();

      // Try to take a photo when storage is full (demonstrates exception handling)
      System.out.println("Attempting to take photo when storage is full:");
      try {
        myCamera.takePhoto("Overflow photo");
      } catch (CameraException e) {
        System.out.println("Exception caught: " + e.getMessage());
      }
      System.out.println();

      // Demonstrate low battery scenario
      System.out.println("Simulating low battery scenario:");
      myCamera.setBatteryLevel(3);
      try {
        myCamera.takePhoto("Low battery photo");
      } catch (CameraException e) {
        System.out.println("Exception caught: " + e.getMessage());
      }
      System.out.println();

      // Power off camera
      System.out.println("Powering off camera...");
      myCamera.powerOff();

      System.out.println("\n=== Demo Complete ===");

    } catch (CameraException e) {
      System.err.println("Camera error: " + e.getMessage());
    }
  }
}
