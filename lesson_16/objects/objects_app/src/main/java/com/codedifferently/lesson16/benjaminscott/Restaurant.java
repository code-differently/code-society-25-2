package com.codedifferently.lesson16.benjaminscott;

public class Restaurant {
  private String name;
  private String[] menuitems = new String[5];
  private int tableCapacity = 20;
  private int currentTables;
  private boolean isOpen;

  public enum RestaurantType {
    FAST_FOOD,
    CASUAL_DINING,
    FINE_DINING,
    CAFE,
    BUFFET
  }

  public Restaurant(
      String name, String[] menuitems, int tableCapacity, int currentTables, boolean isOpen) {
    this.name = name;
    this.menuitems = menuitems;
    this.tableCapacity = tableCapacity;
    this.currentTables = currentTables;
    this.isOpen = isOpen;
  }

  public String getName() {
    return name;
  }

  public String[] getMenuitems() {
    return menuitems;
  }

  public int getTableCapacity() {
    return tableCapacity;
  }

  public int getCurrentTables() {
    return currentTables;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void checkCapacity() {
    if (currentTables == tableCapacity) {
      isOpen = false;
    }
  }

  public void checkIfOpen() {
    if (!isOpen) {
      throw new RestaurantNotOpen("The restaurant is currently not open.");
    }
  }

  public boolean addMenuItem(String item) {
    if (!isOpen) {
      return false;
    }
    for (int i = 0; i < menuitems.length; i++) {
      if (menuitems[i] == null) {
        menuitems[i] = item;
        return true;
      }
    }
    return false;
  }

  public String displayMenu() {
    StringBuilder menu = new StringBuilder("Menu Items:\n");
    for (String item : menuitems) {
      if (item != null) {
        menu.append("- ").append(item).append("\n");
      }
    }
    return menu.toString();
  }

  public boolean seatGuests(int numberOfGuests) {
    if (!isOpen || numberOfGuests <= 0) {
      return false;
    }
    if (currentTables + 1 <= tableCapacity) {
      currentTables++;
      return true;
    }
    return false; 
  }
}
