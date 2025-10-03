/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vscode
 */
public class HomeTest {

  private Home home;

  @BeforeEach
  public void setUp() {
    home =
        new Home(
            HomeType.DUPLEX,
            List.of(RoomType.LIVING_ROOM, RoomType.KITCHEN, RoomType.BEDROOM, RoomType.BATHROOM),
            250000,
            "Downtown");
  }

  @Test
  public void getterAndSetterTest() {
    // Test HomeType getter and setter
    assertTrue(home.getHomeType() == HomeType.DUPLEX);

    // Test rooms getter and setter
    assertTrue(
        home.getRooms()
            .equals(
                List.of(
                    RoomType.LIVING_ROOM, RoomType.KITCHEN, RoomType.BEDROOM, RoomType.BATHROOM)));
    home.setRooms(
        List.of(RoomType.LIVING_ROOM, RoomType.KITCHEN, RoomType.BEDROOM, RoomType.BEDROOM));
    assertTrue(
        home.getRooms()
            .equals(
                List.of(
                    RoomType.LIVING_ROOM, RoomType.KITCHEN, RoomType.BEDROOM, RoomType.BEDROOM)));

    // Test price getter and setter
    assertTrue(home.getSquareFootage() == 250000);
    home.setSquareFootage(300000);
    assertTrue(home.getSquareFootage() == 300000);

    // Test neighborhood getter
    assertTrue(home.getNeighborhood().equals("Downtown"));
  }

  @Test
  public void getNumberOfSpeceficRoomTest() {

    // Given

    // When
    Integer numberOfBedRooms = home.getNumberOfSpeceficRoom(RoomType.BEDROOM);
    Integer numberOfLivingRooms = home.getNumberOfSpeceficRoom(RoomType.LIVING_ROOM);
    Integer numberOfBathrooms = home.getNumberOfSpeceficRoom(RoomType.BATHROOM);
    Integer numberOfKitchens = home.getNumberOfSpeceficRoom(RoomType.KITCHEN);

    // Then
    assertEquals(numberOfBedRooms, 1);
    assertEquals(numberOfLivingRooms, 1);
    assertEquals(numberOfKitchens, 1);
    assertEquals(numberOfBathrooms, 1);
  }

  @Test
  public void getNumberOfSpeceficRoom_RoomNotFound() {

    assertThatThrownBy(() -> home.getNumberOfSpeceficRoom(RoomType.OFFICE))
        .isInstanceOf(RoomNotFoundException.class)
        .hasMessage("This house doesn't have a " + RoomType.OFFICE);
  }

  @Test
  public void getHomeDetailsTest() {
    // Given

    // When
    String expected = "1 Bed 1 Bath 250000 Sq Ft";
    String actual = home.getHomeDetails();
    assertEquals(expected, actual);
    // Then
  }

  @Test
  public void inValidHomeTest() {
    // Given

    assertThatThrownBy(
            () -> new Home(HomeType.DUPLEX, List.of(RoomType.LIVING_ROOM), 250000, "Downtown"))
        .isInstanceOf(InvalidHomeException.class)
        .hasMessage("Home is not habitable");
  }
}
