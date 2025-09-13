/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vscode
 */
public class Home {

  private final HomeType homeType;
  private List<RoomType> rooms;

  private Double squareFootage;
  private String neighborhood;
  private Map<RoomType, Integer> roomCount = new HashMap<>();

  public Home(HomeType homeType, List<RoomType> rooms, Double squareFootage, String neighborhood) {
    this.homeType = homeType;
    this.rooms = rooms;
    this.squareFootage = squareFootage;
    this.neighborhood = neighborhood;
    initRoomCount();
  }

  public HomeType getHomeType() {
    return homeType;
  }

  public void setRooms(List<RoomType> rooms) {
    this.rooms = rooms;
  }

  public List<RoomType> getRooms() {
    return rooms;
  }

  public Double getSquareFootage() {
    return squareFootage;
  }

  public void setSquareFootage(Double price) {
    this.squareFootage = price;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  private void initRoomCount() {

    for (RoomType room : rooms) {

      // get the current value of rooms if the value is not there set it to zero
      Integer value = roomCount.getOrDefault(room, 0);
      // increase the value and create a frequency map
      roomCount.put(room, ++value);
    }
  }

  public Integer getNumberOfSpeceficRoom(RoomType room) {

    if (!roomCount.containsKey(room)) {
      throw new RoomNotFoundException("This house doesn't have a " + room);
    }

    return roomCount.get(room);
  }

  public String getHomeDetails() {

    return "";
  }
}
