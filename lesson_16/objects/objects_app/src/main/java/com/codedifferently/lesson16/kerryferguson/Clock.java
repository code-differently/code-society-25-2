package com.codedifferently.lesson16.kerryferguson;

/**
 * A Clock class that emulates a real-world clock object. This class will represent a digital clock
 * with various features.
 */
import java.util.ArrayList;

public class Clock {
  private String brand;
  private int hour; // 0-23
  private int minute; // 0-59
  private boolean isRunning;
  private ClockMode mode; // enum
  private ArrayList<String> alarms; // collection

  public enum ClockMode {
    STANDARD,
    MILITARY
  }

  // Constructor
  public Clock(String brand, ClockMode mode) {
    this.brand = brand;
    this.mode = mode;
    this.hour = 0;
    this.minute = 0;
    this.isRunning = false;
    this.alarms = new ArrayList<>();
  }

  // Method 1: Set time (uses conditional + throws custom exception)
  public void setTime(int hour, int minute) throws InvalidTimeException {
    if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
      throw new InvalidTimeException("Invalid time: " + hour + ":" + minute);
    }
    this.hour = hour;
    this.minute = minute;
  }

  // Method 2: Add an alarm (uses collection)
  public void addAlarm(String time) throws InvalidTimeException {
    // Validate alarm time format
    if (!isValidTimeFormat(time)) {
      throw new InvalidTimeException("Invalid alarm time format: " + time + ". Use HH:MM format.");
    }
    alarms.add(time);
  }

  // Method 3: Display all alarms (uses loop)
  public String displayAlarms() {
    if (alarms.isEmpty()) {
      return "No alarms set.";
    }

    StringBuilder result = new StringBuilder("Alarms set:\n");
    for (String alarm : alarms) {
      result.append("- ").append(alarm).append("\n");
    }
    return result.toString().trim();
  }

  // Method 4: Check if current time matches an alarm (uses conditional + collection)
  public boolean checkAlarmDue() {
    String currentTime = String.format("%02d:%02d", hour, minute);
    return alarms.contains(currentTime);
  }

  // Method 5: Set time zone offset (uses conditional expression)
  private double timeZoneOffset = 0.0;

  public void setTimeZone(double offset) throws InvalidTimeException {
    if (offset < -12.0 || offset > 14.0) {
      throw new InvalidTimeException(
          "Invalid time zone offset: " + offset + ". Must be between -12.0 and +14.0.");
    }
    this.timeZoneOffset = offset;
  }

  // Get current time display based on clock mode (uses conditional)
  public String getCurrentTime() {
    if (mode == ClockMode.STANDARD) {
      int displayHour = (hour == 0) ? 12 : (hour > 12) ? hour - 12 : hour;
      String amPm = (hour < 12) ? "AM" : "PM";
      return String.format("%d:%02d %s", displayHour, minute, amPm);
    } else {
      return String.format("%02d:%02d", hour, minute);
    }
  }

  // Helper method to validate time format
  private boolean isValidTimeFormat(String time) {
    if (time == null || !time.matches("\\d{2}:\\d{2}")) {
      return false;
    }

    String[] parts = time.split(":");
    int h = Integer.parseInt(parts[0]);
    int m = Integer.parseInt(parts[1]);

    return h >= 0 && h <= 23 && m >= 0 && m <= 59;
  }

  // Toggle clock running state
  public void start() {
    isRunning = true;
  }

  public void stop() {
    isRunning = false;
  }

  // Getters
  public String getBrand() {
    return brand;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public double getTimeZoneOffset() {
    return timeZoneOffset;
  }

  public boolean isRunning() {
    return isRunning;
  }

  public ClockMode getMode() {
    return mode;
  }

  public ArrayList<String> getAlarms() {
    return new ArrayList<>(alarms);
  }
}
