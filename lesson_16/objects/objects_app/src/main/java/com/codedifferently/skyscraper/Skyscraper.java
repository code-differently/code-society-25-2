package com.codedifferently.skyscraper;

import java.util.ArrayList;

public class Skyscraper {
  private String name;
  private final String address;
  private final int numOfFloors;
  private final ArrayList<Integer> numOfRoomsPerFloor;
  private Boolean isOpen;
  private IndustryType industryType;

  public enum IndustryType {
    TECHNOLOGY,
    FINANCE,
    HEALTHCARE,
    EDUCATION,
    ENTERTAINMENT,
    RETAIL,
    HOSPITALITY,
    GOVERNMENT,
    OTHER
  }

  public Skyscraper(
      String name,
      String address,
      int numOfFloors,
      ArrayList<Integer> numOfRoomsPerFloor,
      IndustryType industryType) {
    this.name = name;
    this.address = address;
    this.numOfFloors = numOfFloors;
    this.numOfRoomsPerFloor = numOfRoomsPerFloor;
    this.industryType = industryType;
    this.isOpen = false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public int getNumOfFloors() {
    return numOfFloors;
  }

  public ArrayList<Integer> getNumOfRoomsPerFloor() {
    return numOfRoomsPerFloor;
  }

  public Boolean getIsOpen() {
    return isOpen;
  }

  public void openBuilding() {
    this.isOpen = true;
  }

  public void closeBuilding() {
    this.isOpen = false;
  }

  public IndustryType getIndustryType() {
    return industryType;
  }

  public void setIndustryType(IndustryType industryType) {
    this.industryType = industryType;
  }

  public int getTotalNumberOfRooms() {
    int totalRooms = 0;
    for (int rooms : numOfRoomsPerFloor) {
      totalRooms += rooms;
    }
    return totalRooms;
  }

  public Boolean isIndustryGovernmentFunded() {
    return this.industryType == IndustryType.GOVERNMENT
        || this.industryType == IndustryType.EDUCATION
        || this.industryType == IndustryType.HEALTHCARE;
  }

  public String callPhoneNumber() {
    if (this.isOpen) {
      return "Calling " + this.name + "...";
    } else {
      throw new SkyscraperIsClosedException("Building is closed. Cannot make calls.");
    }
  }

  public String getBuildingInfo() {
    return "Skyscraper Name: "
        + name
        + "\n"
        + "Address: "
        + address
        + "\n"
        + "Number of Floors: "
        + numOfFloors
        + "\n"
        + "Rooms per Floor: "
        + numOfRoomsPerFloor.toString()
        + "\n"
        + "Is Open: "
        + isOpen
        + "\n"
        + "Industry Type: "
        + industryType
        + "\n"
        + "Total Number of Rooms: "
        + getTotalNumberOfRooms()
        + "\n"
        + "Government Funded: "
        + isIndustryGovernmentFunded();
  }

  @Override
  public String toString() {
    return getBuildingInfo();
  }
}
