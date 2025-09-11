package com.codedifferently.lesson16.benjaminscott;

public class Restaurant {
    private String name;
    private String[] menuitems = new String[5];
    private int tableCapacity;
    private int currentTables;
    private boolean isOpen;

    public Restaurant(String name, String[] menuitems, int tableCapacity, int currentTables, boolean isOpen) {
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
}
