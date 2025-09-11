package com.codedifferently.lesson16.calvinrobinson;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive test class for Camera with 5+ test methods Tests all functionality including edge
 * cases and exception handling
 */
public class CameraTest {

  private Camera camera;

  @BeforeEach
  void setUp() {
    // Initialize a fresh camera instance before each test
    camera = new Camera("Canon", "EOS R5", 45.0, 5);
  }

  /** Test 1: Test camera construction and initial state */
  @Test
  void testCameraConstruction() {
    assertEquals("Canon", camera.getBrand());
    assertEquals("EOS R5", camera.getModel());
    assertEquals(45.0, camera.getMegapixels());
    assertEquals(100, camera.getBatteryLevel()); // Should start with full battery
    assertFalse(camera.isPoweredOn()); // Should start powered off
    assertEquals(CameraMode.AUTO, camera.getCurrentMode()); // Default to auto
    assertEquals(0, camera.getPhotoCount()); // No photos initially
    assertEquals(5, camera.getMaxStorageCapacity());
  }

  /** Test 2: Test power on functionality and conditional expressions */
  @Test
  void testPowerOnSuccessful() throws CameraException {
    // Test successful power on
    camera.powerOn();
    assertTrue(camera.isPoweredOn());
    assertEquals(98, camera.getBatteryLevel()); // Should consume 2% battery
  }

  /** Test 3: Test power on with dead battery (exception handling) */
  @Test
  void testPowerOnWithDeadBattery() {
    // Set battery to 0
    camera.setBatteryLevel(0);

    // Should throw CameraException for dead battery
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.powerOn();
            });

    assertTrue(exception.getMessage().contains("Battery is dead"));
    assertFalse(camera.isPoweredOn()); // Should remain off
  }

  /** Test 4: Test photo taking functionality (uses collection) */
  @Test
  void testTakePhotoSuccessful() throws CameraException {
    // Power on camera first
    camera.powerOn();

    // Take a photo
    camera.takePhoto("Beautiful sunset");

    assertEquals(1, camera.getPhotoCount());
    assertEquals(95, camera.getBatteryLevel()); // Should consume 3% battery for photo
  }

  /** Test 5: Test photo taking when camera is off (exception handling) */
  @Test
  void testTakePhotoWhenCameraOff() {
    // Try to take photo without powering on
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.takePhoto("Test photo");
            });

    assertTrue(exception.getMessage().contains("Camera is powered off"));
    assertEquals(0, camera.getPhotoCount()); // No photos should be taken
  }

  /** Test 6: Test storage capacity limit (uses collection and loop) */
  @Test
  void testStorageCapacityLimit() throws CameraException {
    camera.powerOn();

    // Fill up storage capacity (5 photos)
    for (int i = 1; i <= 5; i++) {
      camera.takePhoto("Photo " + i);
    }

    assertEquals(5, camera.getPhotoCount());

    // Try to take one more photo - should throw exception
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.takePhoto("Overflow photo");
            });

    assertTrue(exception.getMessage().contains("Storage capacity full"));
    assertEquals(5, camera.getPhotoCount()); // Should still be 5
  }

  /** Test 7: Test display all photos functionality (uses loop) */
  @Test
  void testDisplayAllPhotos() throws CameraException {
    camera.powerOn();

    // Test with empty storage
    assertDoesNotThrow(() -> camera.displayAllPhotos());

    // Add some photos and test display
    camera.takePhoto("Mountain view");
    camera.takePhoto("City skyline");

    assertDoesNotThrow(() -> camera.displayAllPhotos());
    assertEquals(2, camera.getPhotoCount());
  }

  /** Test 8: Test camera mode changes (uses enum) */
  @Test
  void testCameraModeChange() throws CameraException {
    camera.powerOn();

    // Test changing to different modes
    camera.changeCameraMode(CameraMode.PORTRAIT);
    assertEquals(CameraMode.PORTRAIT, camera.getCurrentMode());

    camera.changeCameraMode(CameraMode.SPORTS);
    assertEquals(CameraMode.SPORTS, camera.getCurrentMode());

    camera.changeCameraMode(CameraMode.NIGHT);
    assertEquals(CameraMode.NIGHT, camera.getCurrentMode());
  }

  /** Test 9: Test camera mode change when powered off */
  @Test
  void testCameraModeChangeWhenOff() {
    // Try to change mode without powering on
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.changeCameraMode(CameraMode.MANUAL);
            });

    assertTrue(exception.getMessage().contains("Camera is powered off"));
    assertEquals(CameraMode.AUTO, camera.getCurrentMode()); // Should remain auto
  }

  /** Test 10: Test camera status functionality */
  @Test
  void testCameraStatus() throws CameraException {
    // Test status when off
    String statusOff = camera.getCameraStatus();
    assertTrue(statusOff.contains("Power: OFF"));
    assertTrue(statusOff.contains("Battery: 100%"));
    assertTrue(statusOff.contains("Photos: 0/5"));

    // Power on and take photos, then check status
    camera.powerOn();
    camera.takePhoto("Test photo 1");
    camera.changeCameraMode(CameraMode.LANDSCAPE);

    String statusOn = camera.getCameraStatus();
    assertTrue(statusOn.contains("Power: ON"));
    assertTrue(statusOn.contains("Battery: 95%")); // 100% - 2% power on - 3% photo
    assertTrue(statusOn.contains("Photos: 1/5"));
    assertTrue(statusOn.contains("Mode: LANDSCAPE"));
    assertTrue(statusOn.contains("Canon EOS R5"));
  }

  /** Test 11: Test low battery photo taking */
  @Test
  void testLowBatteryPhotoTaking() throws CameraException {
    camera.powerOn();
    camera.setBatteryLevel(4); // Set battery below 5%

    // Should throw exception for low battery
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.takePhoto("Low battery photo");
            });

    assertTrue(exception.getMessage().contains("Battery too low"));
  }

  /** Test 12: Test power off functionality */
  @Test
  void testPowerOff() throws CameraException {
    camera.powerOn();
    assertTrue(camera.isPoweredOn());

    camera.powerOff();
    assertFalse(camera.isPoweredOn());
  }

  /** Test 13: Test enum descriptions */
  @Test
  void testCameraModeDescriptions() {
    assertEquals("Automatic mode - camera selects settings", CameraMode.AUTO.getDescription());
    assertEquals("Manual mode - user controls all settings", CameraMode.MANUAL.getDescription());
    assertEquals(
        "Portrait mode - optimized for people photography", CameraMode.PORTRAIT.getDescription());
    assertEquals("Sports mode - fast shutter for action shots", CameraMode.SPORTS.getDescription());
    assertEquals("Landscape mode - optimized for scenery", CameraMode.LANDSCAPE.getDescription());
    assertEquals(
        "Night mode - optimized for low light conditions", CameraMode.NIGHT.getDescription());
    assertEquals("Macro mode - for close-up photography", CameraMode.MACRO.getDescription());
  }

  /** Test 14: Test battery level validation */
  @Test
  void testBatteryLevelValidation() {
    // Test valid battery levels
    camera.setBatteryLevel(50);
    assertEquals(50, camera.getBatteryLevel());

    camera.setBatteryLevel(0);
    assertEquals(0, camera.getBatteryLevel());

    camera.setBatteryLevel(100);
    assertEquals(100, camera.getBatteryLevel());

    // Test invalid battery levels (should not change current value)
    int currentBattery = camera.getBatteryLevel();
    camera.setBatteryLevel(-10);
    assertEquals(currentBattery, camera.getBatteryLevel()); // Should remain unchanged

    camera.setBatteryLevel(150);
    assertEquals(currentBattery, camera.getBatteryLevel()); // Should remain unchanged
  }

  /** Test 15: Test power on when already powered on */
  @Test
  void testPowerOnWhenAlreadyOn() throws CameraException {
    camera.powerOn();
    assertTrue(camera.isPoweredOn());

    // Try to power on again - should throw exception
    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.powerOn();
            });

    assertTrue(exception.getMessage().contains("Camera is already powered on"));
  }

  /** Test 16: Test power off when already off */
  @Test
  void testPowerOffWhenAlreadyOff() {
    assertFalse(camera.isPoweredOn());

    // Should not throw exception when powering off an already off camera
    assertDoesNotThrow(() -> camera.powerOff());
    assertFalse(camera.isPoweredOn());
  }

  /** Test 17: Test display photos when camera is off */
  @Test
  void testDisplayPhotosWhenOff() {
    assertFalse(camera.isPoweredOn());

    CameraException exception =
        assertThrows(
            CameraException.class,
            () -> {
              camera.displayAllPhotos();
            });

    assertTrue(exception.getMessage().contains("Camera is powered off"));
  }

  /** Test 18: Test CameraException with cause */
  @Test
  void testCameraExceptionWithCause() {
    Exception cause = new RuntimeException("Original error");
    CameraException exception = new CameraException("Camera error occurred", cause);

    assertEquals("Camera error occurred", exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  /** Test 19: Test all camera modes */
  @Test
  void testAllCameraModes() throws CameraException {
    camera.powerOn();

    // Test all enum values
    for (CameraMode mode : CameraMode.values()) {
      camera.changeCameraMode(mode);
      assertEquals(mode, camera.getCurrentMode());
    }
  }

  /** Test 20: Test comprehensive camera status at different states */
  @Test
  void testComprehensiveCameraStatus() throws CameraException {
    // Test initial status
    String initialStatus = camera.getCameraStatus();
    assertTrue(initialStatus.contains("Canon EOS R5"));
    assertTrue(initialStatus.contains("Power: OFF"));
    assertTrue(initialStatus.contains("Battery: 100%"));
    assertTrue(initialStatus.contains("Mode: AUTO"));
    assertTrue(initialStatus.contains("Photos: 0/5"));
    assertTrue(initialStatus.contains("Megapixels: 45.0 MP"));

    // Power on and change state
    camera.powerOn();
    camera.setBatteryLevel(75);
    camera.changeCameraMode(CameraMode.MACRO);
    camera.takePhoto("Test macro shot");

    String updatedStatus = camera.getCameraStatus();
    assertTrue(updatedStatus.contains("Power: ON"));
    assertTrue(updatedStatus.contains("Battery: 72%")); // 75% - 3% for photo
    assertTrue(updatedStatus.contains("Mode: MACRO"));
    assertTrue(updatedStatus.contains("Photos: 1/5"));
  }

  /** Test 21: Test edge case - battery exactly at 5% */
  @Test
  void testBatteryAtFivePercent() throws CameraException {
    camera.powerOn();
    camera.setBatteryLevel(5); // Exactly at threshold

    // Should be able to take photo at exactly 5%
    assertDoesNotThrow(() -> camera.takePhoto("Edge case photo"));
    assertEquals(1, camera.getPhotoCount());
  }

  /** Test 22: Test multiple photo operations */
  @Test
  void testMultiplePhotoOperations() throws CameraException {
    camera.powerOn();

    // Take photos and verify battery decreases correctly
    int initialBattery = camera.getBatteryLevel();
    camera.takePhoto("Photo 1");
    camera.takePhoto("Photo 2");
    camera.takePhoto("Photo 3");

    assertEquals(3, camera.getPhotoCount());
    assertEquals(initialBattery - 9, camera.getBatteryLevel()); // 3 photos * 3% each

    // Display photos
    assertDoesNotThrow(() -> camera.displayAllPhotos());
  }

  /** Test 23: Test constructor with different values */
  @Test
  void testConstructorVariations() {
    Camera camera1 = new Camera("Nikon", "D850", 45.7, 10);
    assertEquals("Nikon", camera1.getBrand());
    assertEquals("D850", camera1.getModel());
    assertEquals(45.7, camera1.getMegapixels());
    assertEquals(10, camera1.getMaxStorageCapacity());
    assertEquals(100, camera1.getBatteryLevel());
    assertFalse(camera1.isPoweredOn());
    assertEquals(CameraMode.AUTO, camera1.getCurrentMode());

    Camera camera2 = new Camera("Sony", "A7R IV", 61.0, 20);
    assertEquals("Sony", camera2.getBrand());
    assertEquals("A7R IV", camera2.getModel());
    assertEquals(61.0, camera2.getMegapixels());
    assertEquals(20, camera2.getMaxStorageCapacity());
  }

  /** Test 24: Test all getter methods */
  @Test
  void testAllGetters() throws CameraException {
    assertEquals("Canon", camera.getBrand());
    assertEquals("EOS R5", camera.getModel());
    assertEquals(45.0, camera.getMegapixels());
    assertEquals(100, camera.getBatteryLevel());
    assertFalse(camera.isPoweredOn());
    assertEquals(CameraMode.AUTO, camera.getCurrentMode());
    assertEquals(0, camera.getPhotoCount());
    assertEquals(5, camera.getMaxStorageCapacity());

    // Change state and test getters again
    camera.powerOn();
    camera.setBatteryLevel(80);
    camera.changeCameraMode(CameraMode.NIGHT);
    camera.takePhoto("Night shot");

    assertEquals(80 - 3, camera.getBatteryLevel()); // 80% - 3% for photo
    assertTrue(camera.isPoweredOn());
    assertEquals(CameraMode.NIGHT, camera.getCurrentMode());
    assertEquals(1, camera.getPhotoCount());
  }

  /** Test 25: Test setBatteryLevel edge cases */
  @Test
  void testSetBatteryLevelEdgeCases() {
    // Test boundary values
    camera.setBatteryLevel(0);
    assertEquals(0, camera.getBatteryLevel());

    camera.setBatteryLevel(100);
    assertEquals(100, camera.getBatteryLevel());

    camera.setBatteryLevel(50);
    assertEquals(50, camera.getBatteryLevel());

    // Test invalid values - should not change
    camera.setBatteryLevel(-1);
    assertEquals(50, camera.getBatteryLevel()); // Should remain 50

    camera.setBatteryLevel(101);
    assertEquals(50, camera.getBatteryLevel()); // Should remain 50

    camera.setBatteryLevel(-100);
    assertEquals(50, camera.getBatteryLevel()); // Should remain 50

    camera.setBatteryLevel(200);
    assertEquals(50, camera.getBatteryLevel()); // Should remain 50
  }

  /** Test 26: Test comprehensive scenario - full camera lifecycle */
  @Test
  void testFullCameraLifecycle() throws CameraException {
    // Initial state verification
    assertFalse(camera.isPoweredOn());
    assertEquals(100, camera.getBatteryLevel());
    assertEquals(0, camera.getPhotoCount());
    assertEquals(CameraMode.AUTO, camera.getCurrentMode());

    // Power on
    camera.powerOn();
    assertTrue(camera.isPoweredOn());
    assertEquals(98, camera.getBatteryLevel());

    // Change modes through all available options
    for (CameraMode mode : CameraMode.values()) {
      camera.changeCameraMode(mode);
      assertEquals(mode, camera.getCurrentMode());
    }

    // Take photos until storage is full
    camera.changeCameraMode(CameraMode.SPORTS);
    for (int i = 1; i <= camera.getMaxStorageCapacity(); i++) {
      camera.takePhoto("Sports Photo " + i);
      assertEquals(i, camera.getPhotoCount());
    }

    // Verify storage is full
    assertEquals(camera.getMaxStorageCapacity(), camera.getPhotoCount());

    // Display all photos
    camera.displayAllPhotos();

    // Test status at various points
    String fullStatus = camera.getCameraStatus();
    assertTrue(fullStatus.contains("Power: ON"));
    assertTrue(fullStatus.contains("Mode: SPORTS"));
    assertTrue(fullStatus.contains("Photos: 5/5"));

    // Power off
    camera.powerOff();
    assertFalse(camera.isPoweredOn());
  }

  /** Test 27: Test CameraException constructors thoroughly */
  @Test
  void testCameraExceptionConstructors() {
    // Test message-only constructor
    CameraException ex1 = new CameraException("Simple error message");
    assertEquals("Simple error message", ex1.getMessage());
    assertNull(ex1.getCause());

    // Test message with cause constructor
    RuntimeException cause = new RuntimeException("Root cause");
    CameraException ex2 = new CameraException("Error with cause", cause);
    assertEquals("Error with cause", ex2.getMessage());
    assertEquals(cause, ex2.getCause());

    // Test null message
    CameraException ex3 = new CameraException(null);
    assertNull(ex3.getMessage());

    // Test null cause
    CameraException ex4 = new CameraException("Message", null);
    assertEquals("Message", ex4.getMessage());
    assertNull(ex4.getCause());
  }
}
