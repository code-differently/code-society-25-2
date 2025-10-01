package com.codedifferently.lesson16.kerryferguson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the Clock class. Contains at least 5 test methods as required. */
public class ClockTest {

  private Clock clock;

  @BeforeEach
  void setUp() {
    clock = new Clock("Samsung", Clock.ClockMode.STANDARD);
  }

  @Test
  void testClockCreation() {
    assertNotNull(clock);
    assertEquals("Samsung", clock.getBrand());
    assertEquals(Clock.ClockMode.STANDARD, clock.getMode());
    assertEquals(0, clock.getHour());
    assertEquals(0, clock.getMinute());
    assertFalse(clock.isRunning());
    assertTrue(clock.getAlarms().isEmpty());
  }

  @Test
  void testSetValidTime() throws InvalidTimeException {
    clock.setTime(14, 30);
    assertEquals(14, clock.getHour());
    assertEquals(30, clock.getMinute());
  }

  @Test
  void testSetInvalidTimeThrowsException() {
    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.setTime(25, 30); // Invalid hour
        });

    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.setTime(12, 60); // Invalid minute
        });

    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.setTime(-1, 30); // Negative hour
        });
  }

  @Test
  void testAlarmFeatures() throws InvalidTimeException {
    // Test adding valid alarms
    clock.addAlarm("07:30");
    clock.addAlarm("12:00");

    assertEquals(2, clock.getAlarms().size());
    assertTrue(clock.getAlarms().contains("07:30"));
    assertTrue(clock.getAlarms().contains("12:00"));

    // Test display alarms
    String alarmDisplay = clock.displayAlarms();
    assertTrue(alarmDisplay.contains("07:30"));
    assertTrue(alarmDisplay.contains("12:00"));

    // Test invalid alarm format
    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.addAlarm("25:30"); // Invalid hour
        });

    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.addAlarm("7:30"); // Wrong format (should be 07:30)
        });
  }

  @Test
  void testAlarmDueCheck() throws InvalidTimeException {
    clock.setTime(8, 15);
    clock.addAlarm("08:15");
    clock.addAlarm("09:00");

    assertTrue(clock.checkAlarmDue()); // Should match 08:15

    clock.setTime(8, 30);
    assertFalse(clock.checkAlarmDue()); // Should not match any alarm
  }

  @Test
  void testTimeZoneHandling() throws InvalidTimeException {
    // Test valid time zone offsets
    clock.setTimeZone(-5.0);
    assertEquals(-5.0, clock.getTimeZoneOffset());

    clock.setTimeZone(8.5);
    assertEquals(8.5, clock.getTimeZoneOffset());

    // Test invalid time zone offsets
    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.setTimeZone(-13.0); // Too negative
        });

    assertThrows(
        InvalidTimeException.class,
        () -> {
          clock.setTimeZone(15.0); // Too positive
        });
  }

  @Test
  void testClockModeDisplay() throws InvalidTimeException {
    // Test STANDARD mode (12-hour format)
    Clock standardClock = new Clock("Apple", Clock.ClockMode.STANDARD);
    standardClock.setTime(14, 30);
    assertEquals("2:30 PM", standardClock.getCurrentTime());

    standardClock.setTime(8, 15);
    assertEquals("8:15 AM", standardClock.getCurrentTime());

    standardClock.setTime(0, 0);
    assertEquals("12:00 AM", standardClock.getCurrentTime());

    // Test MILITARY mode (24-hour format)
    Clock militaryClock = new Clock("Casio", Clock.ClockMode.MILITARY);
    militaryClock.setTime(14, 30);
    assertEquals("14:30", militaryClock.getCurrentTime());

    militaryClock.setTime(8, 15);
    assertEquals("08:15", militaryClock.getCurrentTime());
  }

  @Test
  void testClockStartStop() {
    assertFalse(clock.isRunning());

    clock.start();
    assertTrue(clock.isRunning());

    clock.stop();
    assertFalse(clock.isRunning());
  }
}
