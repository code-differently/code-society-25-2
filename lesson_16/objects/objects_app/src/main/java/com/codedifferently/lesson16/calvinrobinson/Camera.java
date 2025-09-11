package com.codedifferently.lesson16.calvinrobinson;

import java.util.ArrayList;
import java.util.List;

/**
 * Camera class representing a real-world digital camera with comprehensive functionality
 *
 * <p>This class demonstrates all required OOP principles: - 5+ member variables of 3+ different
 * types including a collection - Enum type integration - Constructor implementation - 3+ member
 * functions with specific requirements - Custom exception usage
 */
public class Camera {

  // Member variables (5+ of 3+ different types)
  private String brand; // String type
  private String model; // String type
  private double megapixels; // double type
  private int batteryLevel; // int type
  private boolean isPoweredOn; // boolean type
  private CameraMode currentMode; // Enum type
  private List<String> photoMemory; // Collection type (ArrayList)
  private int maxStorageCapacity; // int type

  /**
   * Constructor - initializes a new Camera instance
   *
   * @param brand The camera brand (e.g., "Canon", "Nikon")
   * @param model The camera model
   * @param megapixels The camera's megapixel capacity
   * @param maxStorage Maximum number of photos that can be stored
   */
  public Camera(String brand, String model, double megapixels, int maxStorage) {
    this.brand = brand;
    this.model = model;
    this.megapixels = megapixels;
    this.maxStorageCapacity = maxStorage;
    this.batteryLevel = 100; // Start with full battery
    this.isPoweredOn = false; // Start powered off
    this.currentMode = CameraMode.AUTO; // Default to auto mode
    this.photoMemory = new ArrayList<>(); // Initialize empty photo storage
  }

  /**
   * Function 1: Uses conditional expression Powers on the camera if battery level is sufficient
   *
   * @throws CameraException if battery is too low or camera is already on
   */
  public void powerOn() throws CameraException {
    // Conditional expression - checks battery level and power state
    if (batteryLevel <= 0) {
      throw new CameraException("Cannot power on: Battery is dead. Please charge the camera.");
    } else if (isPoweredOn) {
      throw new CameraException("Camera is already powered on.");
    } else {
      isPoweredOn = true;
      batteryLevel -= 2; // Power on consumes 2% battery
      System.out.println(
          brand + " " + model + " is now powered on. Battery: " + batteryLevel + "%");
    }
  }

  /**
   * Function 2: Uses collection member variable Takes a photo and stores it in the camera's memory
   *
   * @param photoName The name/description of the photo being taken
   * @throws CameraException if camera is off, storage is full, or battery is low
   */
  public void takePhoto(String photoName) throws CameraException {
    // Uses collection member variable (photoMemory)
    if (!isPoweredOn) {
      throw new CameraException("Cannot take photo: Camera is powered off.");
    } else if (batteryLevel < 5) {
      throw new CameraException("Cannot take photo: Battery too low (below 5%).");
    } else if (photoMemory.size() >= maxStorageCapacity) {
      throw new CameraException(
          "Cannot take photo: Storage capacity full (" + maxStorageCapacity + " photos).");
    } else {
      // Add photo to collection
      String photoDetails =
          String.format(
              "Photo: %s | Mode: %s | Quality: %.1fMP", photoName, currentMode.name(), megapixels);
      photoMemory.add(photoDetails);
      batteryLevel -= 3; // Taking photo consumes 3% battery
      System.out.println("Photo taken: " + photoDetails + " | Battery: " + batteryLevel + "%");
    }
  }

  /**
   * Function 3: Uses a loop Displays all photos stored in the camera's memory
   *
   * @throws CameraException if camera is powered off
   */
  public void displayAllPhotos() throws CameraException {
    if (!isPoweredOn) {
      throw new CameraException("Cannot display photos: Camera is powered off.");
    }

    System.out.println(
        "\n=== Photo Gallery (" + photoMemory.size() + "/" + maxStorageCapacity + " photos) ===");

    // Uses a loop to iterate through the collection
    if (photoMemory.isEmpty()) {
      System.out.println("No photos stored in memory.");
    } else {
      for (int i = 0; i < photoMemory.size(); i++) {
        System.out.println((i + 1) + ". " + photoMemory.get(i));
      }
    }
    System.out.println("=== End of Gallery ===\n");
  }

  /**
   * Additional function: Change camera mode
   *
   * @param newMode The new camera mode to set
   * @throws CameraException if camera is powered off
   */
  public void changeCameraMode(CameraMode newMode) throws CameraException {
    if (!isPoweredOn) {
      throw new CameraException("Cannot change mode: Camera is powered off.");
    } else {
      this.currentMode = newMode;
      System.out.println(
          "Camera mode changed to: " + newMode.name() + " - " + newMode.getDescription());
    }
  }

  /**
   * Additional function: Check camera status
   *
   * @return String containing current camera status information
   */
  public String getCameraStatus() {
    return String.format(
        "Camera Status:\nBrand: %s %s\nPower: %s\nBattery: %d%%\nMode: %s\nPhotos: %d/%d\nMegapixels: %.1f MP",
        brand,
        model,
        isPoweredOn ? "ON" : "OFF",
        batteryLevel,
        currentMode.name(),
        photoMemory.size(),
        maxStorageCapacity,
        megapixels);
  }

  /** Additional function: Power off the camera */
  public void powerOff() {
    if (isPoweredOn) {
      isPoweredOn = false;
      System.out.println(brand + " " + model + " is now powered off.");
    }
  }

  // Getter methods
  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public double getMegapixels() {
    return megapixels;
  }

  public int getBatteryLevel() {
    return batteryLevel;
  }

  public boolean isPoweredOn() {
    return isPoweredOn;
  }

  public CameraMode getCurrentMode() {
    return currentMode;
  }

  public int getPhotoCount() {
    return photoMemory.size();
  }

  public int getMaxStorageCapacity() {
    return maxStorageCapacity;
  }

  // Setter methods (with validation)
  public void setBatteryLevel(int batteryLevel) {
    if (batteryLevel >= 0 && batteryLevel <= 100) {
      this.batteryLevel = batteryLevel;
    }
  }
}
