package com.codedifferently.lesson16.trinitiejackson;

import java.util.ArrayList;
import java.util.List;

public class Phone {
  private int batteryLevel;
  private boolean isOn;
  private boolean callInProgress;
  private Volume currentVolume;
  private final List<String> textReady;
  private final String brand;

  public Phone(int batteryLevel, List<String> textReady, String brand) {
    this.batteryLevel = batteryLevel;
    this.isOn = false;
    this.callInProgress = false;
    this.currentVolume = Volume.LOW;
    this.textReady = new ArrayList<>(textReady);
    this.brand = brand;
  }

  public boolean isOn() {
    return isOn;
  }

  public int getBatteryLevel() {
    return batteryLevel;
  }

  public String getBrand() {
    return brand;
  }

  public boolean isCallInProgress() {
    return callInProgress;
  }

  public Volume getCurrentVolume() {
    return currentVolume;
  }

  public List<String> getTextReady() {
    return new ArrayList<>(textReady);
  }

  public void powerOn() {
    isOn = true;
  }

  public void powerOff() {
    isOn = false;
  }

  public void setCurrentVolume(Volume volume) {
    this.currentVolume = volume;
  }

  public boolean lowBattery() {
    return batteryLevel < 21;
  }

  public void startCall() throws CriticalBatteryException {
    if (batteryLevel < 5) {
      throw new CriticalBatteryException("Battery too low to start a call");
    }

    if (isOn && batteryLevel > 0) {
      batteryLevel -= 2;
      callInProgress = true;
    } else {
      callInProgress = false;
    }
  }

  public String printAllVolumeSettings() {
    StringBuilder sb = new StringBuilder();

    for (Volume v : Volume.values()) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(v.name());
    }

    return sb.toString();
  }

  public void addTextReady(String text) {
    textReady.add(text);
  }
}
