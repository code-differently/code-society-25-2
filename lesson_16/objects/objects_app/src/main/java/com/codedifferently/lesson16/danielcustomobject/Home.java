/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

import java.util.List;

/**
 * @author vscode
 */
public class Home {

  private HomeType homeType;
  private List<RoomType> rooms;
  private Integer numberOfStories;
  private Double price;
  private String neighborhood;



  public HomeType getHomeType() {
    return homeType;
  }

  public void setHomeType(HomeType homeType) {
    this.homeType = homeType;
  }

  public void setRooms(List<RoomType> rooms) {
    this.rooms = rooms;
  }

  public List<RoomType> getRooms() {
    return rooms;
  }

  public Integer getNumberOfStories() {
    return numberOfStories;
  }

  public void setNumberOfStories(Integer numberOfStories) {
    this.numberOfStories = numberOfStories;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

 

  public Integer getNumberOfBathrooms() {

    Integer count = 0;
    for (String string : rooms) {
      
    }
    return 0;
  }





}
