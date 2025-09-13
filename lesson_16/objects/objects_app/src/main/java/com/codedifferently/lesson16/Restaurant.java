package com.codedifferently.lesson16;

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

    //Getters
    public String getName() {
        return name;
    }
}

//Getters and Setters
