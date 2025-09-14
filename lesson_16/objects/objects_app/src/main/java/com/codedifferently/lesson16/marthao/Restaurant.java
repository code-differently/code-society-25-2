package com.codedifferently.lesson16.marthao;

public class Restaurant {
    private String name;
    private double rating;
    private CuisineType cuisineType;
    private String address;
    private String[] menus;



    public Restaurant(String name, double rating, CuisineType cuisineType, String address, String[] menus) {
        this.name = name;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.address = address;
        this.menus = menus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isHighlyRated() {
        return this.rating >= 4.0;
    }
  
    public static class MenuNotFoundException extends Exception {
        public MenuNotFoundException(String message) {
            super(message);
        }
    }

    public String getMenuByTime(String timeOfDay) throws MenuNotFoundException {
    for (int i = 0; i < menus.length; i++) {
        if (timeOfDay.equalsIgnoreCase("Morning") && menus[i].equalsIgnoreCase("Breakfast")) {
            return menus[i];
        } else if (timeOfDay.equalsIgnoreCase("Afternoon") && menus[i].equalsIgnoreCase("Lunch")) {
            return menus[i];
        } else if (timeOfDay.equalsIgnoreCase("Evening") && menus[i].equalsIgnoreCase("Dinner")) {
            return menus[i];
        }
    }
    throw new MenuNotFoundException("No menu available for " + timeOfDay);
    }
}