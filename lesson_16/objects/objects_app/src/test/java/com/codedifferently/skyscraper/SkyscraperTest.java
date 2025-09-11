package com.codedifferently.skyscraper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkyscraperTest {

  private Skyscraper skyscraper;
  private ArrayList<Integer> roomsPerFloor;

  @BeforeEach
  void setUp() {
    roomsPerFloor = new ArrayList<>(Arrays.asList(10, 15, 12, 8, 20));
    skyscraper =
        new Skyscraper(
            "Tech Tower",
            "123 Innovation St",
            5,
            roomsPerFloor,
            Skyscraper.IndustryType.TECHNOLOGY);
  }

  @Test
  void testConstructor() {
    // Test that all fields are properly initialized
    assertThat(skyscraper.getName()).isEqualTo("Tech Tower");
    assertThat(skyscraper.getAddress()).isEqualTo("123 Innovation St");
    assertThat(skyscraper.getNumOfFloors()).isEqualTo(5);
    assertThat(skyscraper.getNumOfRoomsPerFloor()).isEqualTo(roomsPerFloor);
    assertThat(skyscraper.getIndustryType()).isEqualTo(Skyscraper.IndustryType.TECHNOLOGY);
    assertThat(skyscraper.getIsOpen()).isFalse(); // Should default to false
  }

  @Test
  void testSetAndGetName() {
    // Test name setter and getter
    skyscraper.setName("New Building Name");
    assertThat(skyscraper.getName()).isEqualTo("New Building Name");
  }

  @Test
  void testGetAddress() {
    // Test address getter (address is final so no setter)
    assertThat(skyscraper.getAddress()).isEqualTo("123 Innovation St");
  }

  @Test
  void testGetNumOfFloors() {
    // Test floors getter (numOfFloors is final so no setter)
    assertThat(skyscraper.getNumOfFloors()).isEqualTo(5);
  }

  @Test
  void testGetNumOfRoomsPerFloor() {
    // Test rooms per floor getter
    assertThat(skyscraper.getNumOfRoomsPerFloor()).isEqualTo(roomsPerFloor);
    assertThat(skyscraper.getNumOfRoomsPerFloor()).hasSize(5);
  }

  @Test
  void testOpenAndCloseBuilding() {
    // Test opening building
    assertThat(skyscraper.getIsOpen()).isFalse();

    skyscraper.openBuilding();
    assertThat(skyscraper.getIsOpen()).isTrue();

    // Test closing building
    skyscraper.closeBuilding();
    assertThat(skyscraper.getIsOpen()).isFalse();
  }

  @Test
  void testSetAndGetIndustryType() {
    // Test industry type setter and getter
    skyscraper.setIndustryType(Skyscraper.IndustryType.FINANCE);
    assertThat(skyscraper.getIndustryType()).isEqualTo(Skyscraper.IndustryType.FINANCE);

    skyscraper.setIndustryType(Skyscraper.IndustryType.HEALTHCARE);
    assertThat(skyscraper.getIndustryType()).isEqualTo(Skyscraper.IndustryType.HEALTHCARE);
  }

  @Test
  void testGetTotalNumberOfRooms() {
    // Test total rooms calculation: 10 + 15 + 12 + 8 + 20 = 65
    assertThat(skyscraper.getTotalNumberOfRooms()).isEqualTo(65);
  }

  @Test
  void testGetTotalNumberOfRoomsWithEmptyFloors() {
    // Test with empty list
    ArrayList<Integer> emptyRooms = new ArrayList<>();
    Skyscraper emptySkyscraper =
        new Skyscraper(
            "Empty Building", "456 Empty St", 0, emptyRooms, Skyscraper.IndustryType.OTHER);
    assertThat(emptySkyscraper.getTotalNumberOfRooms()).isEqualTo(0);
  }

  @Test
  void testGetTotalNumberOfRoomsWithSingleFloor() {
    // Test with single floor
    ArrayList<Integer> singleFloor = new ArrayList<>(Arrays.asList(25));
    Skyscraper singleFloorBuilding =
        new Skyscraper(
            "Single Floor", "789 Single St", 1, singleFloor, Skyscraper.IndustryType.RETAIL);
    assertThat(singleFloorBuilding.getTotalNumberOfRooms()).isEqualTo(25);
  }

  @Test
  void testIsIndustryGovernmentFunded_Government() {
    // Test government industry
    skyscraper.setIndustryType(Skyscraper.IndustryType.GOVERNMENT);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isTrue();
  }

  @Test
  void testIsIndustryGovernmentFunded_Education() {
    // Test education industry
    skyscraper.setIndustryType(Skyscraper.IndustryType.EDUCATION);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isTrue();
  }

  @Test
  void testIsIndustryGovernmentFunded_Healthcare() {
    // Test healthcare industry
    skyscraper.setIndustryType(Skyscraper.IndustryType.HEALTHCARE);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isTrue();
  }

  @Test
  void testIsIndustryGovernmentFunded_NotGovernmentFunded() {
    // Test non-government funded industries
    skyscraper.setIndustryType(Skyscraper.IndustryType.TECHNOLOGY);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();

    skyscraper.setIndustryType(Skyscraper.IndustryType.FINANCE);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();

    skyscraper.setIndustryType(Skyscraper.IndustryType.ENTERTAINMENT);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();

    skyscraper.setIndustryType(Skyscraper.IndustryType.RETAIL);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();

    skyscraper.setIndustryType(Skyscraper.IndustryType.HOSPITALITY);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();

    skyscraper.setIndustryType(Skyscraper.IndustryType.OTHER);
    assertThat(skyscraper.isIndustryGovernmentFunded()).isFalse();
  }

  @Test
  void testCallPhoneNumber_WhenOpen() {
    // Test calling when building is open
    skyscraper.openBuilding();
    String result = skyscraper.callPhoneNumber();
    assertThat(result).isEqualTo("Calling Tech Tower...");
  }

  @Test
  void testCallPhoneNumber_WhenClosed() {
    // Test calling when building is closed - should throw exception
    assertThat(skyscraper.getIsOpen()).isFalse(); // Ensure building is closed

    assertThatThrownBy(() -> skyscraper.callPhoneNumber())
        .isInstanceOf(SkyscraperIsClosedException.class)
        .hasMessage("Building is closed. Cannot make calls.");
  }

  @Test
  void testGetBuildingInfo() {
    // Test building info string generation
    String expectedInfo =
        "Skyscraper Name: Tech Tower\n"
            + "Address: 123 Innovation St\n"
            + "Number of Floors: 5\n"
            + "Rooms per Floor: [10, 15, 12, 8, 20]\n"
            + "Is Open: false\n"
            + "Industry Type: TECHNOLOGY\n"
            + "Total Number of Rooms: 65\n"
            + "Government Funded: false";

    assertThat(skyscraper.getBuildingInfo()).isEqualTo(expectedInfo);
  }

  @Test
  void testGetBuildingInfo_WhenOpen() {
    // Test building info when building is open
    skyscraper.openBuilding();
    String buildingInfo = skyscraper.getBuildingInfo();
    assertThat(buildingInfo).contains("Is Open: true");
  }

  @Test
  void testGetBuildingInfo_WithGovernmentFunding() {
    // Test building info with government funded industry
    skyscraper.setIndustryType(Skyscraper.IndustryType.EDUCATION);
    String buildingInfo = skyscraper.getBuildingInfo();
    assertThat(buildingInfo).contains("Government Funded: true");
    assertThat(buildingInfo).contains("Industry Type: EDUCATION");
  }

  @Test
  void testToString() {
    // Test toString method (should return same as getBuildingInfo)
    assertThat(skyscraper.toString()).isEqualTo(skyscraper.getBuildingInfo());
  }

  @Test
  void testAllIndustryTypes() {
    // Test all industry type enum values
    Skyscraper.IndustryType[] allTypes = Skyscraper.IndustryType.values();
    assertThat(allTypes).hasSize(9);
    assertThat(allTypes)
        .contains(
            Skyscraper.IndustryType.TECHNOLOGY,
            Skyscraper.IndustryType.FINANCE,
            Skyscraper.IndustryType.HEALTHCARE,
            Skyscraper.IndustryType.EDUCATION,
            Skyscraper.IndustryType.ENTERTAINMENT,
            Skyscraper.IndustryType.RETAIL,
            Skyscraper.IndustryType.HOSPITALITY,
            Skyscraper.IndustryType.GOVERNMENT,
            Skyscraper.IndustryType.OTHER);
  }

  @Test
  void testConstructorWithDifferentParameters() {
    // Test constructor with different parameters
    ArrayList<Integer> differentRooms = new ArrayList<>(Arrays.asList(5, 10));
    Skyscraper differentBuilding =
        new Skyscraper(
            "Finance Center", "456 Money Ave", 2, differentRooms, Skyscraper.IndustryType.FINANCE);

    assertThat(differentBuilding.getName()).isEqualTo("Finance Center");
    assertThat(differentBuilding.getAddress()).isEqualTo("456 Money Ave");
    assertThat(differentBuilding.getNumOfFloors()).isEqualTo(2);
    assertThat(differentBuilding.getTotalNumberOfRooms()).isEqualTo(15);
    assertThat(differentBuilding.getIndustryType()).isEqualTo(Skyscraper.IndustryType.FINANCE);
    assertThat(differentBuilding.isIndustryGovernmentFunded()).isFalse();
  }
}
