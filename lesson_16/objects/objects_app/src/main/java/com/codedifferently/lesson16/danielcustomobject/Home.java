/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

import java.util.List;

/**
 *
 * @author vscode
 */
public class Home {

    
    private HomeType homeType;
    private List<String> rooms;
    private Integer numberOfStories;
    private Double price;
    private String neighborhood;

    public Home(HomeType homeType,List<String> rooms,Integer numberOfStories,Double price,String neighborhood) {
        this.homeType = homeType;
        this.rooms = rooms;
        this.numberOfStories = numberOfStories;
        this.price = price;
        this.neighborhood = neighborhood;
    }

    public HomeType getHomeType() {
        return null;
    }
    public void setHomeType(HomeType homeType) {
        this.homeType = null;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = null;
    }

    public List<String> getRooms() {
        return null;
    }
    public Integer getNumberOfStories() {
        return null;
    }
    public void setNumberOfStories(Integer numberOfStories) {
        this.numberOfStories = null;
    }
    public Double getPrice() {
        return null;
    }
    public void setPrice(Double price) {
        this.price = null;
    }

    public String getNeighborhood() {
        return null;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = null;
    }
    

}
