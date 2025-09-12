package com.codedifferently.lesson16.benjaminscott;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RestaurantTest {

  @Test
  void testAddMenuItem() {
    String[] initialMenu = new String[5];
    Restaurant restaurant = new Restaurant("Test Restaurant", initialMenu, 20, 0, true);

    boolean firstAdd = restaurant.addMenuItem("Burger");
    boolean secondAdd = restaurant.addMenuItem("Pizza");

    assertTrue(firstAdd, "Should successfully add first item");
    assertTrue(secondAdd, "Should successfully add second item");
    String[] menu = restaurant.getMenuitems();
    assertEquals("Burger", menu[0]);
    assertEquals("Pizza", menu[1]);
  }

  @Test
  void testDisplayMenu() {
    String[] initialMenu = {"Burger", "Pizza", null, null, null};
    Restaurant restaurant = new Restaurant("Test Restaurant", initialMenu, 20, 0, true);

    String displayedMenu = restaurant.displayMenu();

    assertTrue(displayedMenu.contains("Burger"));
    assertTrue(displayedMenu.contains("Pizza"));
  }

  @Test
  void testSeatGuests() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 20, 0, true);
    boolean seated = restaurant.seatGuests(4);
    assertTrue(seated, "Should successfully seat guests");
    assertEquals(1, restaurant.getCurrentTables());
  }

  @Test
  void testSeatGuestsWhenFull() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 1, 1, true);
    boolean seated = restaurant.seatGuests(4);
    assertFalse(seated, "Should not seat guests when full");
    assertEquals(1, restaurant.getCurrentTables());
  }

  @Test
  void testAddMenuItemWhenClosed() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 20, 0, false);
    boolean added = restaurant.addMenuItem("Burger");
    assertFalse(added, "Should not add items when restaurant is closed");
    String[] menu = restaurant.getMenuitems();
    assertEquals(null, menu[0]);
  }

  @Test
  void testAddMenuItemWhenFull() {
    String[] initialMenu = {"Item1", "Item2", "Item3", "Item4", "Item5"};
    Restaurant restaurant = new Restaurant("Test Restaurant", initialMenu, 20, 0, true);
    boolean added = restaurant.addMenuItem("Extra Item");
    assertFalse(added, "Should not add items when menu is full");
  }

  @Test
  void testCheckCapacityWhenNotFull() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 5, 3, true);
    restaurant.checkCapacity();
    assertTrue(restaurant.isOpen(), "Restaurant should remain open when not at capacity");
  }

  @Test
  void testCheckCapacityWhenFull() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 5, 5, true);
    restaurant.checkCapacity();
    assertFalse(restaurant.isOpen(), "Restaurant should close when at capacity");
  }

  @Test
  void testSeatGuestsWithZeroGuests() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 20, 0, true);
    boolean seated = restaurant.seatGuests(0);
    assertFalse(seated, "Should not seat zero guests");
    assertEquals(0, restaurant.getCurrentTables(), "Table count should not change");
  }

  @Test
  void testSeatGuestsWithNegativeGuests() {
    Restaurant restaurant = new Restaurant("Test Restaurant", new String[5], 20, 0, true);
    boolean seated = restaurant.seatGuests(-1);
    assertFalse(seated, "Should not seat negative number of guests");
    assertEquals(0, restaurant.getCurrentTables(), "Table count should not change");
  }

  @Test
  void testDisplayEmptyMenu() {
    String[] emptyMenu = new String[5];
    Restaurant restaurant = new Restaurant("Test Restaurant", emptyMenu, 20, 0, true);
    String displayedMenu = restaurant.displayMenu();
    assertEquals("Menu Items:\n", displayedMenu, "Empty menu should only show header");
  }

  @Test
  void testRestaurantTypeValues() {
    Restaurant.RestaurantType[] types = Restaurant.RestaurantType.values();
    assertEquals(5, types.length, "Should have 5 restaurant types");
    assertEquals(Restaurant.RestaurantType.FAST_FOOD, types[0]);
    assertEquals(Restaurant.RestaurantType.CASUAL_DINING, types[1]);
    assertEquals(Restaurant.RestaurantType.FINE_DINING, types[2]);
    assertEquals(Restaurant.RestaurantType.CAFE, types[3]);
    assertEquals(Restaurant.RestaurantType.BUFFET, types[4]);
  }

  @Test
  void testGetName() {
    String restaurantName = "Test Restaurant";
    Restaurant restaurant = new Restaurant(restaurantName, new String[5], 20, 0, true);
    assertEquals(
        restaurantName, restaurant.getName(), "getName should return the correct restaurant name");
  }
}
