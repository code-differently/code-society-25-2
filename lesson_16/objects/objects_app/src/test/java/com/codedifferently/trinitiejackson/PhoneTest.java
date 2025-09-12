package com.codedifferently.trinitiejackson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.codedifferently.lesson16.trinitiejackson.CriticalBatteryException;
import com.codedifferently.lesson16.trinitiejackson.Phone;
import com.codedifferently.lesson16.trinitiejackson.Volume;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneTest {
  private Phone phone1;
  private Phone phone2;

  @BeforeEach
  public void setUp() {
    phone1 =
        new Phone(
            50,
            false,
            false,
            Volume.LOW,
            new ArrayList<>(List.of("Hello", "How are you?")),
            "Samsung");
    phone2 =
        new Phone(
            2, false, false, Volume.HIGH, new ArrayList<>(List.of("Goodbye", "Why?")), "Apple");
  }

  @Test
  public void testPowerOn() {
    phone1.powerOn();
    assertThat(phone1.isOn()).isTrue();
  }

  @Test
  public void testGetBatteryLevel() {
    assertThat(phone1.getBatteryLevel()).isEqualTo(50);
  }

  @Test
  public void testLowBattery() {
    assertThat(phone2.getBatteryLevel()).isLessThanOrEqualTo(21);
    assertThat(phone2.lowBattery()).isTrue();
  }

  @Test
  public void testGetBrand() {
    assertThat(phone1.getBrand()).isEqualTo("Samsung");
  }

  @Test
  public void testStartCall() {
    phone1.powerOn();
    phone1.startCall();

    assertThat(phone1.isOn()).isTrue();
    assertThat(phone1.getBatteryLevel()).isGreaterThan(5);
    assertThat(phone1.isCallInProgress()).isTrue();
  }

  @Test
  public void testStartCallCriticalBatteryThrowsException() {
    assertThatThrownBy(() -> phone2.startCall())
        .isInstanceOf(CriticalBatteryException.class)
        .hasMessageContaining("Battery too low to start a call");
  }

  @Test
  public void testGetCurrentVolume() {
    assertThat(phone1.getCurrentVolume()).isEqualTo(Volume.LOW);
  }

  @Test
  public void testPrintAllVolumeSettings() {
    String allVolumes = phone1.printAllVolumeSettings();
    assertThat(allVolumes).isEqualTo("MUTE, LOW, MEDIUM, HIGH");
  }

  @Test
  public void testGetTextReady() {
    List<String> texts = phone1.getTextReady();
    assertThat(texts.size()).isEqualTo(2);
    assertThat(texts).contains("Hello", "How are you?");
  }

  @Test
  public void testAddTextReady() {
    phone1.addTextReady("Ok");

    List<String> texts = phone1.getTextReady();
    assertThat(texts.size()).isEqualTo(3);
    assertThat(texts).contains("Ok");
  }
}
